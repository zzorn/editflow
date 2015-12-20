package org.editflow.parameter;

import org.demoflow.node.DemoNode;
import org.demoflow.parameter.calculator.CalculationContext;
import org.demoflow.parameter.calculator.Calculator;
import org.demoflow.parameter.range.Range;
import org.flowutils.Symbol;

/**
 * Information about a parameter in a Parametrized class.
 */
public interface Variable<T> extends DemoNode {

    /**
     * @return unique id of the parameter within the Parametrized class where it is.
     */
    Symbol getId();

    /**
     * @return the Parametrized class that this parameter belongs to.
     */
    Parametrized getHost();

    /**
     * @return the current value of the parameter.
     */
    T get();

    /**
     * Sets a parameter value.  Also updates the initialValue to the newValue.
     * @param newValue new value for the parameter.  The newValue is clamped to the allowed range before being stored.
     *                 No copy is made of the passed in parameter even if it is a vector or similar mutable value.
     */
    void set(T newValue);

    /**
     * @param newValue new value for the parameter.  The newValue is clamped to the allowed range before being stored.
     *                 No copy is made of the passed in parameter even if it is a vector or similar mutable value.
     * @param alsoSetInitialValue if true, the initial value is also updated to newValue (defaults to true).
     */
    void set(T newValue, boolean alsoSetInitialValue);

    /**
     * @return the allowed range for this parameter.
     */
    Range<T> getRange();

    /**
     * @return the calculator used to update the value for the parameter over time, or null if it does not change over time.
     */
    Calculator<T> getCalculator();

    /**
     * @param calculator the calculator used to update the value for the parameter over time, or null if it does not change over time.
     * @return the provided calculator, for easier chaining of operations.
     */
    <C extends Calculator<T>> C setCalculator(C calculator);

    /**
     * @return if true, the parameter does not change over time.
     *         It may however have a calculator that will be used to calculate it before setup.
     */
    boolean isConstant();

    /**
     * @return the type of this parameter.
     */
    Class<T> getType();

    /**
     * Uses the calculator to update the parameter value, if available.
     * @param calculationContext context to pass to the calculator.
     */
    void recalculateParameter(CalculationContext calculationContext);

    /**
     * Reset the value to the initial value.
     * Also resets any dynamic state in the calculator.
     */
    void resetToInitialValue();

    /**
     * @return the manually edited value.
     */
    T getDefaultValue();

    /**
     * @param listener listener to notify about changes to parameter value.
     */
    void addParameterListener(ParameterListener<T> listener);

    /**
     * @param listener listener to remove.
     */
    void removeParameterListener(ParameterListener<T> listener);
}
