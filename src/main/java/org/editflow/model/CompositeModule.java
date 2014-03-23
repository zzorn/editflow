package org.editflow.model;

import org.flowutils.Check;

import java.util.*;

/**
 *
 */
// TODO: Need some kind of context system to allow e.g. trunks that tell their branches their position along the trunk, etc. (basically for loop index)
public class CompositeModule extends BaseModule {

    // Contained modules in this composite module
    // Sorted in processing order
    private List<Module> modules = new ArrayList<Module>();

    // Map specifying the source property for each linked target property
    private Map<Prop, Prop> links = new HashMap<Prop, Prop>();

    /**
     * Adds a component module to this composite module.
     * @param module module to add
     */
    public void addModule(Module module) {
        Check.notContained(module, modules, "child modules");

        modules.add(module);
    }

    /**
     * Removes the specified module.
     * Removes any links to or from the module as well.
     */
    public void removeModule(Module module) {
        Check.contained(module, modules, "child modules");

        // Remove any links to and from the module
        for (Iterator<Map.Entry<Prop, Prop>> iterator = links.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Prop, Prop> entry = iterator.next();
            if (entry.getKey().getModule() == module ||
                entry.getValue().getModule() == module) iterator.remove();
        }

        // Remove module
        modules.remove(module);
    }


    /**
     * Adds a link from the source to the target property.  Replaces any previous source for the target.
     * @param sourceProperty
     * @param targetProperty
     */
    public void addLink(Prop sourceProperty,
                        Prop targetProperty) {
        // Check validity
        checkPropertyFound(sourceProperty, true);
        checkPropertyFound(targetProperty, false);

        // Add link
        final Prop oldSource = links.put(targetProperty, sourceProperty);

        // Check for cycles
        // TODO: Sort modules in processing order using links, check that there are no links going back.
        sortModules();
        if (hasCycle()) {
            // If cycle found, restore link and return error
            links.put(targetProperty, oldSource);
            sortModules();
            throw new IllegalArgumentException("Can not add link, would create cyclical dependency");
        }
    }

    public void removeLink(Prop sourceProperty, Prop targetProperty) {
        if (links.get(targetProperty) == sourceProperty) {
            links.remove(targetProperty);
        }
    }

    /**
     * Adds an outwards visible property to the composite module.
     *
     * @param name name of the property
     * @param type type of the property value
     * @param direction whether the property is readable or writeable or both from outside the module.
     * @param defaultValue default and initial value for the property.
     * @param description human readable description of the property.  Should not be empty.
     */
    public void addProperty(String name, Class type, Direction direction, Object defaultValue, String description) {
        super.addProperty(name, type, direction, defaultValue, description);
    }

    /**
     * Removes the specified property, and any link connected to it.
     */
    public void removeProperty(String name) {
        // Remove any links to and from the property
        for (Iterator<Map.Entry<Prop, Prop>> iterator = links.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Prop, Prop> entry = iterator.next();
            if (isModuleProperty(entry.getKey(), name) ||
                isModuleProperty(entry.getValue(), name)) iterator.remove();
        }

        // Remove module property
        super.removeProperty(name);
    }

    private boolean isModuleProperty(final Prop prop, String name) {
        return prop.getModule() == this &&
               prop.getName().equals(name);
    }

    private void sortModules() {
        // TODO
    }

    private boolean hasCycle() {
        // TODO: Should return true if there is a cyclical link between modules
        return false;
    }

    private void checkPropertyFound(Prop property, boolean source) {
        Module module = property.getModule();
        String propertyName = property.getName();

        String dirName = source ? "source" : "target";

        // Check that module exists
        if (this != module && !modules.contains(module)) throw new IllegalArgumentException("Unknown "+dirName+" module");

        // Check that property exists
        if (!module.hasProp(propertyName)) throw new IllegalArgumentException("Unknown "+dirName+" module");

        // Check direction
        final Direction direction = module.getProp(propertyName).getDirection();
        boolean requiredDir = module == this ? !source : source; // Invert direction for module properties.
        if (!direction.supportsDirection(requiredDir)) throw new IllegalArgumentException("The property '"+property+"' of module '"+module+"' does not support the specified link direction.");
    }

    @Override protected BaseModule createCopy() {
        final CompositeModule copy = new CompositeModule();

        // Copy modules
        // TODO

        // Copy links
        // TODO

        // (superclass copies properties)

        return copy;
    }



    @Override protected void registerProperties() {
        // Nothing to do
    }

}
