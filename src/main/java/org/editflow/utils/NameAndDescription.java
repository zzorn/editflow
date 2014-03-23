package org.editflow.utils;

/**
 * User readable name and description for something.
 */
public interface NameAndDescription {

    /**
     * @return user readable name.
     */
    String getName();

    /**
     * @return user readable description, or empty or null if none available.
     */
    String getDescription();

}
