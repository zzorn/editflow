package org.editflow.utils;

import org.editflow.Editable;

/**
 * Base class that just implements the properties in the NameAndDescription interface.
 */
public class NameAndDescriptionBase implements NameAndDescription {

    private String name;
    private String description;

    public NameAndDescriptionBase() {
        this(null, null);
    }

    public NameAndDescriptionBase(String name) {
        this(name, null);
    }

    public NameAndDescriptionBase(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override final public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    @Override public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    @Override public String toString() {
        return name;
    }

    protected final void extractNameAndDescriptionFromAnnotation(Editable editable, String defaultName) {
        if (editable != null) {
            setName(editable.name());
            setDescription(editable.desc());
        }
        else {
            setName(defaultName);
            setDescription(null);
        }
    }


}
