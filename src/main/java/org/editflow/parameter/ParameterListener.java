package org.editflow.parameter;

/**
 * Listener that is notified when a parameter changes.
 */
public interface ParameterListener<T> {

    /**
     * @param parameter parameter that changed.
     * @param newValue new value of parameter.
     */
    void onChanged(Variable<T> parameter, T newValue);

    /**
     * Manually specified default value changed.
     * @param parameter parameter that changed.
     * @param newValue new default value of parameter.
     */
    void onDefaultValueChanged(Variable<T> parameter, T newValue);

    /**
     * Called when the calculator has been changed.
     * @param parameter the parameter that changed.
     * @param newCalculator the new calculator, or null to use the default value.
     */
    void onCalculatorChanged(Variable<T> parameter, Calculator<T> newCalculator);

}
