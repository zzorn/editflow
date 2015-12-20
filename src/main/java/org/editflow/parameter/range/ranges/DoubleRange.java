package org.editflow.parameter.range.ranges;

import org.demoflow.parameter.range.RangeBase;
import org.flowutils.Check;
import org.flowutils.MathUtils;
import org.flowutils.random.RandomSequence;

import static org.flowutils.Check.notNull;

/**
 * A range for a double valued floating point value.
 */
public final class DoubleRange extends RangeBase<Double> {

    private final double min;
    private final double max;
    private final double defaultValue;
    private final double standardDeviation;

    public static final DoubleRange FULL = new DoubleRange(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, 100);
    public static final DoubleRange POSITIVE = new DoubleRange(0, Double.POSITIVE_INFINITY, 0, 100);
    public static final DoubleRange ONE_OR_LARGER = new DoubleRange(1, Double.POSITIVE_INFINITY, 1, 100);
    public static final DoubleRange NEGATIVE = new DoubleRange(Double.NEGATIVE_INFINITY, 0, 0, 100);
    public static final DoubleRange ZERO_TO_ONE = new DoubleRange(0, 1);
    public static final DoubleRange MINUS_ONE_TO_ONE = new DoubleRange(-1, 1);
    public static final DoubleRange SMALL = new DoubleRange(-100, 100);
    public static final DoubleRange SMALL_NON_NEGATIVE = new DoubleRange(0, 100);

    public DoubleRange(double min, double max) {
        this(min, max, 0);
    }

    public DoubleRange(double min, double max, double defaultValue) {
        this(min, max, defaultValue, 1);
    }

    public DoubleRange(double min, double max, double defaultValue, double standardDeviation) {
        super(Double.class);
        Check.greater(max, "max", min, "min");
        Check.normalNumber(defaultValue, "defaultValue");
        Check.normalNumber(standardDeviation, "standardDeviation");

        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        this.standardDeviation = standardDeviation;
    }

    @Override public Double clampToRange(Double originalValue) {
        notNull(originalValue, "originalValue");
        return MathUtils.clampToRange(originalValue, min, max);
    }

    @Override protected Double createRandomValue(RandomSequence randomSequence) {
        return randomSequence.nextDouble(min, max);
    }

    @Override protected Double doMutateValue(Double value, float mutationAmount, RandomSequence randomSequence) {
        return value + mutationAmount * standardDeviation * randomSequence.nextGaussian();
    }

    @Override protected Double createCopy(Double source) {
        return source;
    }

    @Override protected Double createDefaultValue() {
        return defaultValue;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    @Override public Double interpolate(double t, Double a, Double b, Double out) {
        return a + t * (b - a);
    }
}
