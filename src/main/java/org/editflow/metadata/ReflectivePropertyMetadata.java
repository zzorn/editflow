package org.editflow.metadata;

import org.editflow.Editable;
import org.editflow.utils.NameAndDescriptionBase;
import org.flowutils.Check;
import org.flowutils.Symbol;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.flowutils.Check.notNull;

/**
 * Property metadata implementation that uses reflection to read and change the properties.
 */
public final class ReflectivePropertyMetadata extends NameAndDescriptionBase implements PropertyMetadata {

    private final Symbol propertyId;
    private final Class hostClass;
    private final Class propertyType;
    private final Method getter;
    private final Method setter;

    /**
     * @param hostClass the class that the property is for.
     * @param getter the getter method used to get the property.
     *               Used to derive the setter method based on the getter name.
     */
    public ReflectivePropertyMetadata(Class hostClass, Method getter) {
        notNull(hostClass, "hostClass");
        notNull(getter, "getter");

        // Determine identifier for the property
        propertyId = Symbol.get(getPropertyName(hostClass, getter));

        // Determine property value type
        propertyType = getter.getReturnType();

        // Get name and description, if annotated
        extractNameAndDescriptionFromAnnotation(getter.getAnnotation(Editable.class), propertyId.toString());

        this.hostClass = hostClass;
        this.getter = getter;
        this.setter = findSetter(hostClass, propertyId, propertyType);
    }

    private String getPropertyName(Class type, Method getter) {
        String getterName = getter.getName();

        if (getterName.startsWith("get")) {
            getterName = getterName.substring(3);
        }
        else if (getterName.startsWith("is")) {
            getterName = getterName.substring(2);
        }
        else {
            throw new IllegalStateException("Unknown getter prefix for getter method named '"+getterName+"' in class " + type.getName());
        }

        return getterName;
    }

    private Method findSetter(Class hostClass, Symbol propertyId, Class propertyType) {
        String setterName = "set" + propertyId;

        try {
            final Method method = hostClass.getMethod(setterName, propertyType);
            if (isSetter(method)) {
                // Setter found.
                return method;
            }
            else {
                // The setter was private, or static, or not a valid setter for some other reason.
                return null;
            }

        } catch (NoSuchMethodException e) {
            // Setter not available, property is read only
            return null;
        }
    }

    private boolean isSetter(Method method) {
        return method.getReturnType() == Void.class &&
               method.getParameterTypes().length == 1 &&
               Modifier.isPublic(method.getModifiers()) &&
               !Modifier.isStatic(method.getModifiers());
    }

    @Override public <T> T get(Object bean) {
        Check.notNull(bean, "bean");

        // Get value
        try {
            return (T) getter.invoke(bean);
        } catch (Exception e) {
            throw new IllegalStateException("Could not invoke getter for property " + getName() + " on object of type " + hostClass.getName(), e);
        }
    }

    @Override public <T> void set(Object bean, T value) {
        Check.notNull(bean, "bean");

        // Ensure we are editable
        if (!isEditable()) {
            throw new IllegalStateException("The property " + getName() + " on an object of type " + hostClass.getName() + " is read only, can can not be written");
        }

        // Change value
        try {
            setter.invoke(bean, value);
        } catch (Exception e) {
            throw new IllegalStateException("Could not invoke setter for property " + getName() + " on object of type " + hostClass.getName(), e);
        }
    }

    @Override public Class getType() {
        return propertyType;
    }

    @Override public Symbol getPropertyId() {
        return propertyId;
    }

    @Override public boolean isEditable() {
        return setter != null;
    }

    public Class getHostClass() {
        return hostClass;
    }
}
