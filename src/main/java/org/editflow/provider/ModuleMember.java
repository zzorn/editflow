package org.editflow.provider;

import org.flowutils.Symbol;

/**
 * Something contained in a module.
 */
public interface ModuleMember {

    /**
     * @return unique id within the module it is defined in.
     */
    Symbol getId();

    /**
     * @return user readable name.
     */
    String getName();

    /**
     * @return user readable description, e.g. for tooltips.
     */
    String getDescription();

    /**
     * @return filename or resource handle of the icon to use for this.
     */
    String getIconId();



}
