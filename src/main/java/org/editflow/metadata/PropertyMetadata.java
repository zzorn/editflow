package org.editflow.metadata;

import org.editflow.utils.NameAndDescription;
import org.flowutils.Symbol;

/**
 * Information about a property in a bean type.
 */
public interface PropertyMetadata extends NameAndDescription {

    /**
     * @return the type of value accepted by the property.
     */
    Class getType();

    /**
     * @return id of this property.  Should be unique within the class the property is contained in.
     */
    Symbol getPropertyId();

    /**
     * @return true if this property can be edited, false if it is read-only.
     */
    boolean isEditable();

    /**
     * @return the value of this property for the specified bean.
     * Throws an exception if it could not be retrieved for some reason.
     */
    <T> T get(Object bean);

    /**
     * Sets the value of this property for the specified bean to the specified value.
     * Throws an exception if it could not be set for some reason.
     */
    <T> void set(Object bean, T value);
}
