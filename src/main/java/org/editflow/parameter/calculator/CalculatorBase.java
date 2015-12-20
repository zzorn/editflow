package org.editflow.parameter.calculator;

import org.demoflow.node.DemoNode;
import org.demoflow.parameter.Parameter;
import org.demoflow.parameter.ParametrizedBase;
import org.flowutils.Symbol;

/**
 * Common functionality for calculators.
 */
public abstract class CalculatorBase<T> extends ParametrizedBase implements Calculator<T> {

    // Override if needed
    @Override public void onParameterChanged(Parameter parameter, Symbol id, Object value) {
    }

    @Override public final T calculate(CalculationContext calculationContext, T currentValue, Parameter<T> parameter) {

        // Calculate parameters if needed
        recalculateParameters(calculationContext);

        // Calculate our value
        return doCalculate(calculationContext, currentValue, parameter);
    }

    @Override public final void resetState() {
        // Reset children
        resetParametersToInitialValues();

        // Reset ourselves
        doResetState();
    }

    /**
     * Calculate the value with this calculator.
     * @param calculationContext context with opengl stuff if needed.
     * @param currentValue current value.  If mutable (e.g. a Vector3), this instance should be updated and returned.
     * @param parameter more information about the parameter whose value we are calculating.
     * @return new value (or updated currentValue if it is a mutable object)
     */
    protected abstract T doCalculate(CalculationContext calculationContext, T currentValue, Parameter<T> parameter);

    /**
     * Reset any changing state of this calculator to the starting values.
     */
    protected abstract void doResetState();

    @Override public String getName() {
        return getClass().getSimpleName().replace("Calculator", "");
    }

    @Override public int getDepth() {
        // Calculators are shown on the same conceptual level as the parameters that they calculate values for, so do not add any depth.
        final DemoNode parent = getParent();
        return parent == null ? 0 : parent.getDepth();
    }

}
