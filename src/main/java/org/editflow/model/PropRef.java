package org.editflow.model;

import org.flowutils.Check;

/**
 * Reference to a property of some module.
 */
public final class PropRef {
    private final String moduleName;
    private final String propertyName;

    public PropRef(String moduleName, String propertyName) {
        Check.identifier(moduleName, "moduleName");
        Check.identifier(propertyName, "propertyName");

        this.moduleName = moduleName;
        this.propertyName = propertyName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override public String toString() {
        return moduleName + "." + propertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropRef propRef = (PropRef) o;

        if (!moduleName.equals(propRef.moduleName)) return false;
        if (!propertyName.equals(propRef.propertyName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = moduleName.hashCode();
        result = 31 * result + propertyName.hashCode();
        return result;
    }
}
