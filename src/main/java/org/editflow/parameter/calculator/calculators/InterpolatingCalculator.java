package org.editflow.parameter.calculator.calculators;

import org.demoflow.interpolator.Interpolator;
import org.demoflow.parameter.Parameter;
import org.demoflow.parameter.calculator.CalculationContext;
import org.demoflow.parameter.calculator.CalculatorBase;
import org.demoflow.utils.gradient.Gradient;
import org.demoflow.utils.gradient.GradientImpl;

/**
 * Uses a Gradient to store values at different relative time points over the duration of the effect (0..1),
 * and interpolates them to calculate the value at a specific time.
 */
public final class InterpolatingCalculator<T> extends CalculatorBase<T> {


    private final Gradient<T> gradient = new GradientImpl<>();

    /**
     * @return gradient with the changes to the parameter value over the duration of the effect.
     *         A position of 0 refers to the start of the effect, and 1 to the end of the effect.
     */
    public Gradient<T> getGradient() {
        return gradient;
    }

    /**
     * Adds a value at a specified time with linear interpolation from the previous value.
     * @param pos relative position on the timeline (0 = start of effect, 1 = end of effect).
     * @param value value at the time
     */
    public void addPoint(double pos, T value) {
        gradient.addPoint(pos, value);
    }

    /**
     * Adds a value at a specified time.
     * @param pos relative position on the timeline (0 = start of effect, 1 = end of effect).
     * @param value value at the time
     * @param interpolator interpolation to use to interpolate from the previous value.
     */
    public void addPoint(double pos, T value, Interpolator interpolator) {
        gradient.addPoint(pos, value, interpolator);
    }

    @Override protected T doCalculate(CalculationContext calculationContext, T currentValue, Parameter<T> parameter) {
        return gradient.getValueAt(calculationContext.getRelativeEffectPosition(), parameter.getRange());
    }

    @Override protected void doResetState() {
        // No changing state
    }

    @Override public Class<T> getReturnType() {
        return null;
    }
}
