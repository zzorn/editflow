package org.editflow.metadata;

import org.editflow.Editable;
import org.editflow.utils.NameAndDescriptionBase;
import org.flowutils.Check;
import org.flowutils.Symbol;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.flowutils.Check.notNull;

/**
 * BeanMetadata implementation that uses reflection to read metadata about the class.
 */
public final class ReflectiveBeanMetadata extends NameAndDescriptionBase implements BeanMetadata {

    private final Class type;
    private final Map<Symbol, PropertyMetadata> propertyMetadatas = new LinkedHashMap<Symbol, PropertyMetadata>();

    private final Map<Symbol, PropertyMetadata> unmodifiablePropertyMetadatas = Collections.unmodifiableMap(propertyMetadatas);

    private static final Comparator<PropertyMetadata> PROPERTY_METADATA_COMPARATOR = new Comparator<PropertyMetadata>() {
        @Override public int compare(PropertyMetadata m1, PropertyMetadata m2) {
            return m1.getName().compareTo(m2.getName());
        }
    };

    public ReflectiveBeanMetadata(Class type) {
        notNull(type, "type");

        this.type = type;

        extractNameAndDescriptionFromAnnotation(getEditableAnnotation(type), type.getSimpleName());

        extractPropertyMetadata(type);
    }

    private void extractPropertyMetadata(Class type) {

        // Extract property metadatas
        final Method[] methods = type.getMethods();
        List<PropertyMetadata> foundProperties = new ArrayList<PropertyMetadata>();
        for (Method method : methods) {
            if (isEditableProperty(method) && isGetter(method)) {
                foundProperties. add(new ReflectivePropertyMetadata(type, method));
            }
        }

        // Sort property metadatas by property name
        Collections.sort(foundProperties, PROPERTY_METADATA_COMPARATOR);

        // Add properties to the linked map in the sorted order
        for (PropertyMetadata foundProperty : foundProperties) {
            propertyMetadatas.put(foundProperty.getPropertyId(), foundProperty);
        }
    }

    private boolean isEditableProperty(Method method) {
        // NOTE: For now require that the Editable annotation is present for the property to be editable.
        return method.getAnnotation(Editable.class) != null;
    }

    private Editable getEditableAnnotation(Class type) {
        final Annotation editableAnnotation = type.getAnnotation(Editable.class);

        if (editableAnnotation == null) return null;
        else return (Editable) editableAnnotation;
    }

    private boolean isGetter(Method method) {
        final int modifiers = method.getModifiers();
        return Modifier.isPublic(modifiers) &&
               !Modifier.isStatic(modifiers) &&
               method.getParameterTypes().length == 0 &&
               (method.getName().startsWith("get") ||
                method.getName().startsWith("is") );
    }


    @Override public Map<Symbol, PropertyMetadata> getPropertyMetadata() {
        return unmodifiablePropertyMetadatas;
    }

    public Class getType() {
        return type;
    }
}
