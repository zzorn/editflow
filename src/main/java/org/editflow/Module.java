package org.editflow;

import org.editflow.parameter.Variable;
import org.editflow.provider.Type;
import org.flowutils.Symbol;

import java.util.List;

/**
 * Something that may contain definitions, variables, and imports.
 * The definitions may be private, only visible to children, or public, visible to outside users.
 *
 * Calculator extends this as well, providing a return value.
 */
// TODO: Add calculator function definitions
public interface Module {

    /**
     * @return the parent module
     */
    Module getParent();


    /**
     * @return the modules that this module imports.
     *         public functions and variables from imported modules can be used in this module and nested scopes.
     */
    List<Module> getImports();

    /**
     * Imports the specified module, if it is not already imported.
     */
    void addImport(Module moduleToImport);

    /**
     * Removes the specified import.
     */
    void removeImport(Module importToRemove);


    /**
     * @return the values that this module has.
     */
    List<Variable> getVariables();

    /**
     * @return the value with the specified id, or null if this calculator has no such value.
     */
    Variable getVariable(Symbol id);

    /**
     * Adds a new variable to this module.
     * @param id unique id of the variable within this module.
     * @param type type of the value.
     * @return the created and added Variable object.
     */
    <T> Variable<T> addVariable(String id, Type<T> type);

    /**
     * Adds a new variable to this module.
     * @param id unique id of the variable within this module.
     * @param type type of the value.
     * @param defaultValue initial and default value for the variable.
     * @return the created and added variable object.
     */
    <T> Variable<T> addVariable(String id, Type<T> type, T defaultValue);

    /**
     * Removes the specified variable from this module, if it is in this module.
     */
    void removeVariable(Variable variable);


}
