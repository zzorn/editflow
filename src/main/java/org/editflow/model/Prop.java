package org.editflow.model;

/**
 *
 */
public interface Prop {

    /**
     * @return identifier of the property.  Should be java style identifier.
     */
    String getName();

    /**
     * @return human readable description of the property.
     */
    String getDescription();

    /**
     * @return type of values that the property can hold.
     */
    Class getType();

    /**
     * @return whether the property can be read, written, or both.
     */
    Direction getDirection();

    /**
     * @return the module that the property belongs to.
     */
    Module getModule();

    /**
     * @return current value of the property.
     */
    Object getValue();

    /**
     * @param value new value for the property.
     */
    void setValue(Object value);

    /**
     * @return default value of the property.
     */
    Object getDefaultValue();
}
