package org.editflow.parameter;

import org.demoflow.parameter.calculator.Calculator;

/**
 *
 */
public abstract class ParameterListenerAdapter<T> implements ParameterListener<T> {

    @Override public void onChanged(Variable<T> parameter, T newValue) {
    }

    @Override public void onDefaultValueChanged(Variable<T> parameter, T newValue) {
    }

    @Override public void onCalculatorChanged(Variable<T> parameter, Calculator<T> newCalculator) {
    }
}
