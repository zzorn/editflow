package org.editflow.parameter.calculator;

import org.demoflow.parameter.Parameter;
import org.demoflow.parameter.Parametrized;

/**
 * Calculates a parameter value.
 * May use own parameters as inputs.
 */
public interface Calculator<T> extends Parametrized {

    /**
     * Reset any dynamic calculation state.
     */
    void resetState();

    /**
     * Calculate a value.
     *
     * @param calculationContext context with current time position.
     * @param currentValue current value of the parameter that we are calculating a value for.
     *                     If the value is a mutable object (e.g. Vector3), this should be changed.
     * @param parameter the parameter whose value we are recalculating.
     * @return new value for the parameter.
     */
    T calculate(CalculationContext calculationContext, T currentValue, Parameter<T> parameter);

    /**
     * @return the type of value that this calculator produces, or null if the type of the value is not determined (e.g. interpolating calculator).
     */
    Class<T> getReturnType();

}
