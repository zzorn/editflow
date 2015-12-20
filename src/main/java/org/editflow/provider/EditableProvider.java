package org.editflow.provider;

/**
 * Abstracts the way properties are accessed.
 */
public interface EditableProvider {

    Module getRootModule();

    /**
     * @return the type of the specified editable object, or null if it is not an editable object.
     */
    Type getTypeOfObject(Object objectInstance);

}
