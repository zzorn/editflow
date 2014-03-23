package org.editflow.model;

import org.flowutils.Check;

import java.util.*;

/**
 *
 */
public abstract class BaseModule implements Module {

    private String name;
    private String description;

    private Map<String, Prop> propertyLookup = new LinkedHashMap<String, Prop>();

    @Override final public void init() {
        registerProperties();
    }

    protected BaseModule() {
    }

    protected BaseModule(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Called when the module is initialized.
     * Should register any properties provided by the module with addProperty().
     */
    protected abstract void registerProperties();

    /**
     * Adds a property to the module.
     * @param name name of the property
     * @param type type of the property value
     * @param direction whether the property is readable or writeable or both from outside the module.
     * @param defaultValue default and initial value for the property.
     * @param description human readable description of the property.  Should not be empty.
     */
    protected void addProperty(String name,
                               Class type,
                               Direction direction,
                               Object defaultValue,
                               String description) {
        Check.identifier(name, "name");
        Check.notNull(type, "type");
        Check.nonEmptyString(description, "description");
        Check.notContained(name, propertyLookup, "existing properties");

        Prop prop = new DynamicProp(name,
                                    type,
                                    description,
                                    direction,
                                    this,
                                    defaultValue,
                                    defaultValue);

        propertyLookup.put(name, prop);
    }

    /**
     * Removes the specified property.
     */
    protected void removeProperty(String name) {
        propertyLookup.remove(name);
    }


    @Override public final Prop getProp(String name) {
        checkPropertyExists(name);

        return propertyLookup.get(name);
    }

    @Override public final boolean hasProp(String name) {
        return propertyLookup.containsKey(name);
    }

    @Override public final Object getPropertyValue(String name) {
        checkPropertyExists(name);

        return propertyLookup.get(name).getValue();
    }

    @Override public final void setPropertyValue(String name, Object value) {
        checkPropertyExists(name);

        // Ensure that the value is compatible
        if (value == null || !propertyLookup.get(name).getType().isInstance(value)) {
            throw new IllegalArgumentException("Can not assign '"+value+"' to property " + name + " of module " + this);
        }

        propertyLookup.get(name).setValue(value);
    }

    private void checkPropertyExists(String name) {
        if (!propertyLookup.containsKey(name)) throw new IllegalArgumentException("Unknown property '"+name+"'");
    }

    @Override public final Collection<Prop> getProperties() {
        return propertyLookup.values();
    }

    @Override public Module copy() {
        // Create basic copy
        final BaseModule copy = createCopy();

        // Copy properties
        for (Prop prop : propertyLookup.values()) {
            copy.addProperty(prop.getName(),
                             prop.getType(),
                             prop.getDirection(),
                             prop.getDefaultValue(),
                             prop.getDescription());
        }
        
        return copy;
    }

    protected abstract BaseModule createCopy();
}
