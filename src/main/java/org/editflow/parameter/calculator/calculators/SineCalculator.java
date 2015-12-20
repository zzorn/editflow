package org.editflow.parameter.calculator.calculators;

import org.demoflow.parameter.Parameter;
import org.demoflow.parameter.calculator.CalculationContext;
import org.demoflow.parameter.calculator.CalculatorBase;

import static org.flowutils.MathUtils.Tau;
import static org.flowutils.MathUtils.wrap0To1;

/**
 * Parameter calculator that creates a sine wave that varies over time.
 * The wave length in seconds, amplitude, result offset, and phase shift can all be configured or updated on the fly with other calculators.
 */
public final class SineCalculator extends CalculatorBase<Double> {

    public final Parameter<Double> waveLength;
    public final Parameter<Double> offset;
    public final Parameter<Double> amplitude;
    public final Parameter<Double> phaseShift;

    private double currentPhase;

    public SineCalculator() {
        this(1.0, 0.0, 1.0, 0.0);
    }

    public SineCalculator(final double waveLength,
                          final double offset,
                          final double amplitude,
                          final double phaseShift) {
        this.waveLength = addParameter("waveLength", waveLength);
        this.offset = addParameter("offset", offset);
        this.amplitude = addParameter("amplitude", amplitude);
        this.phaseShift = addParameter("phaseShift", phaseShift);
    }

    @Override
    protected Double doCalculate(CalculationContext calculationContext, Double currentValue, Parameter<Double> parameter) {

        final double waveLength = this.waveLength.get();

        // Update phase, wrap to wavelength
        final double deltaTime = calculationContext.deltaTimeSeconds();
        currentPhase = wrap0To1(currentPhase + (waveLength == 0 ? 0 : deltaTime / waveLength));

        // Calculate value
        double result = Math.sin((currentPhase + phaseShift.get()) * Tau) * amplitude.get();

        // Add offset to result
        return result + offset.get();
    }

    @Override protected void doResetState() {
        // Reset current phase
        currentPhase = 0;
    }

    @Override public Class<Double> getReturnType() {
        return Double.class;
    }
}
