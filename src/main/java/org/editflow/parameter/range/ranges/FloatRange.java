package org.editflow.parameter.range.ranges;

import org.demoflow.parameter.range.RangeBase;
import org.flowutils.Check;
import org.flowutils.MathUtils;
import org.flowutils.random.RandomSequence;

import static org.flowutils.Check.notNull;

/**
 * A range for a floating point value.
 */
public final class FloatRange extends RangeBase<Float> {

    private final float min;
    private final float max;
    private final float defaultValue;
    private final float standardDeviation;

    public static final FloatRange FULL = new FloatRange(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 0, 100);
    public static final FloatRange POSITIVE = new FloatRange(0, Float.POSITIVE_INFINITY, 0, 100);
    public static final FloatRange ONE_OR_LARGER = new FloatRange(1, Float.POSITIVE_INFINITY, 1, 100);
    public static final FloatRange NEGATIVE = new FloatRange(Float.NEGATIVE_INFINITY, 0, 0, 100);
    public static final FloatRange ZERO_TO_ONE = new FloatRange(0, 1, 0.5f, 0.25f);
    public static final FloatRange MINUS_ONE_TO_ONE = new FloatRange(-1, 1, 0f, 0.5f);
    public static final FloatRange SMALL = new FloatRange(-100, 100, 0, 10);
    public static final FloatRange SMALL_NON_NEGATIVE = new FloatRange(0, 100, 0, 10);

    public FloatRange(float min, float max) {
        this(min, max, 0);
    }

    public FloatRange(float min, float max, float defaultValue) {
        this(min, max, defaultValue, 1);
    }

    public FloatRange(float min, float max, float defaultValue, float standardDeviation) {
        super(Float.class);
        Check.greater(max, "max", min, "min");
        Check.normalNumber(defaultValue, "defaultValue");
        Check.normalNumber(standardDeviation, "standardDeviation");

        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        this.standardDeviation = standardDeviation;
    }

    @Override public Float clampToRange(Float originalValue) {
        notNull(originalValue, "originalValue");
        return MathUtils.clampToRange(originalValue, min, max);
    }

    @Override protected Float createRandomValue(RandomSequence randomSequence) {
        return randomSequence.nextFloat(min, max);
    }

    @Override protected Float doMutateValue(Float value, float mutationAmount, RandomSequence randomSequence) {
        return value + mutationAmount * standardDeviation * randomSequence.nextGaussianFloat();
    }

    @Override protected Float createCopy(Float source) {
        return source;
    }

    @Override protected Float createDefaultValue() {
        return defaultValue;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getStandardDeviation() {
        return standardDeviation;
    }

    @Override public Float interpolate(double t, Float a, Float  b, Float  out) {
        return (float) (a + t * (b - a));
    }

}
