package org.editflow.provider;

import java.util.List;

/**
 *
 */
public interface Module extends ModuleMember {

    List<Module> getSubModules();

    List<Type> getTypes();

    // TODO: Global properties / constants?
    // TODO: functions / calculators.

    // TODO: add listener support

    // TODO: Object instances?  Object library?  Save and load / (xml)serialization support for that.

}
