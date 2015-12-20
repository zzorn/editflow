package org.editflow.provider;

import org.flowutils.Symbol;

import java.util.List;

/**
 *
 */
public interface ObjectType<T> extends Type<T> {

    /**
     * @return the properties available for the this object type.
     */
    List<Property> getProperties();

    /**
     * @return the property with the specified id, or null if not available.
     */
    Property getProperty(Symbol propertyId);

}
