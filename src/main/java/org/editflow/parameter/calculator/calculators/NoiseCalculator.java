package org.editflow.parameter.calculator.calculators;

import org.demoflow.parameter.Parameter;
import org.demoflow.parameter.calculator.CalculationContext;
import org.demoflow.parameter.calculator.CalculatorBase;
import org.flowutils.SimplexGradientNoise;

/**
 * Generates 1D noise that changes over time.
 */
public final class NoiseCalculator extends CalculatorBase<Double> {

    public final Parameter<Double> amplitude;
    public final Parameter<Double> offset;
    public final Parameter<Double> wavelength;
    public final Parameter<Double> phase;

    private double currentPhase;

    /**
     * Generates random noise with a frequency of 1Hz and output values from -1 to 1.
     */
    public NoiseCalculator() {
        this(1.0);
    }

    /**
     * By default generates random noise with a frequency of 1Hz and output values from -1 to 1.
     * @param wavelength wavelength of the noise in seconds.
     */
    public NoiseCalculator(double wavelength) {
        this(wavelength, 0.0);
    }

    /**
     * By default generates random noise with a frequency of 1Hz and output values from -1 to 1.
     * @param wavelength wavelength of the noise in seconds.
     * @param offset offset to add to the result
     */
    public NoiseCalculator(double wavelength, double offset) {
        this(wavelength, offset, 1.0);
    }

    /**
     * By default generates random noise with a frequency of 1Hz and output values from -1 to 1.
     * @param wavelength wavelength of the noise in seconds.
     * @param offset offset to add to the result
     * @param amplitude scaling to multiply the result with
     */
    public NoiseCalculator(double wavelength, double offset, double amplitude) {
        this(wavelength, offset, amplitude, 0.0);
    }

    /**
     * By default generates random noise with a frequency of 1Hz and output values from -1 to 1.
     * @param wavelength wavelength of the noise in seconds.
     * @param offset offset to add to the result
     * @param amplitude scaling to multiply the result with
     * @param phase can be used to phase shift the noise
     */
    public NoiseCalculator(double wavelength, double offset, double amplitude, double phase) {
        this.amplitude = addParameter("amplitude", amplitude);
        this.offset    = addParameter("offset", offset);
        this.wavelength = addParameter("wavelength", wavelength);
        this.phase     = addParameter("phase", phase);
    }

    @Override
    protected Double doCalculate(CalculationContext calculationContext, Double currentValue, Parameter<Double> parameter) {

        // Update phase
        final double wavelength = this.wavelength.get();
        if (wavelength != 0) {
            currentPhase += calculationContext.deltaTimeSeconds() / wavelength;
        }

        // Calculate value
        double noise = SimplexGradientNoise.sdnoise1(currentPhase + phase.get());

        // Apply amplitude and offset
        return noise  * amplitude.get() + offset.get();
    }

    @Override protected void doResetState() {
        currentPhase = 0;
    }

    @Override public Class<Double> getReturnType() {
        return Double.class;
    }
}
