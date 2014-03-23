package org.editflow.model;

import com.sun.xml.internal.bind.v2.model.core.PropertyInfo;
import org.flowutils.Check;

/**
 * Represents a dynamic property.
 */
public class DynamicProp implements Prop {

    private final String name;
    private final Class type;
    private final String description;
    private final Direction direction;
    private final Module module;
    private final Object defaultValue;

    private Object value;

    public DynamicProp(String name,
                       Class type,
                       String description,
                       Direction direction,
                       Module module,
                       Object value,
                       Object defaultValue) {
        Check.identifier(name, "name");
        Check.notNull(type, "type");
        Check.nonEmptyString(description, "description");
        Check.notNull(direction, "direction");
        Check.notNull(module, "module");

        this.name = name;
        this.type = type;
        this.description = description;
        this.direction = direction;
        this.module = module;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    @Override public String getName() {
        return name;
    }

    @Override public Class getType() {
        return type;
    }

    @Override public String getDescription() {
        return description;
    }

    @Override public Direction getDirection() {
        return direction;
    }

    @Override public Module getModule() {
        return module;
    }

    @Override public Object getValue() {
        return value;
    }

    @Override public void setValue(Object value) {
        this.value = value;
    }

    @Override public Object getDefaultValue() {
        return defaultValue;
    }
}
