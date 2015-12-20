package org.editflow.provider;

import org.flowutils.Symbol;

/**
 * Wraps information about a property.
 */
public interface Property {

    Symbol getId();

    String getName();
    String getDescription();

    ObjectType getHostType();

    Type getType();

    Range getRange();

    /**
     * @return default value for new instances.
     */
    Object getDefaultValue();


    // TODO: Support for calculators?  On update call, recalculate property value.  Possibly pass in update context or object?

    // TODO: Add change listener support


    void set(Object editedObject, Object value);

    void setBoolean(Object editedObject, boolean value);
    void setByte(Object editedObject, byte value);
    void setShort(Object editedObject, short value);
    void setInt(Object editedObject, int value);
    void setLong(Object editedObject, long value);
    void setFloat(Object editedObject, float value);
    void setDouble(Object editedObject, double value);

    Object get(Object editedObject);

    boolean getBoolean(Object editedObject);
    byte getByte(Object editedObject);
    short getShort(Object editedObject);
    int getInt(Object editedObject);
    long getLong(Object editedObject);
    float getFloat(Object editedObject);
    double getDouble(Object editedObject);



}
