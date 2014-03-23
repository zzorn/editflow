package org.editflow.model;

import java.util.Collection;

/**
 * Editable component.
 */
public interface Module {

    /**
     * @return name of this module.
     */
    String getName();

    /**
     * @return human readable description of this module.
     */
    String getDescription();

    /**
     * Should be called when the module is created.  Can be used to do various setup.
     */
    void init();

    /**
     * @return list of properties with information and values.
     */
    Collection<Prop> getProperties();

    /**
     * @return property with the specified name.
     * Throws exception if the property is not found.
     */
    Prop getProp(String name);

    /**
     * @return true if the module has a property with the specified name.
     */
    boolean hasProp(String name);

    /**
     * @param name name of property whose value should be returned.
     * @return value of the specified property.
     * Throws exception if the property is not found.
     */
    Object getPropertyValue(String name);

    /**
     * @param name property whose value should be updated.
     * @param value new value for the property.
     * Throws exception if the property is not found.
     */
    void setPropertyValue(String name, Object value);

    /**
     * @return an independent deep copy of this module.
     */
    Module copy();
}
