package org.editflow.parameter.range.ranges;

import org.demoflow.parameter.range.RangeBase;
import org.flowutils.Check;
import org.flowutils.MathUtils;
import org.flowutils.random.RandomSequence;

import static org.flowutils.Check.notNull;

/**
 * A range for an integer value.
 */
public final class IntRange extends RangeBase<Integer> {

    private final int min;
    private final int max;
    private final int defaultValue;
    private final float standardDeviation;

    public static final IntRange FULL = new IntRange(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 100);
    public static final IntRange POSITIVE = new IntRange(0, Integer.MAX_VALUE, 0, 100);
    public static final IntRange ONE_OR_LARGER = new IntRange(1, Integer.MAX_VALUE, 1, 100);
    public static final IntRange NEGATIVE = new IntRange(Integer.MIN_VALUE, 0, 0, 100);
    public static final IntRange ZERO_TO_ONE = new IntRange(0, 1, 0, 1);
    public static final IntRange MINUS_ONE_TO_ONE = new IntRange(-1, 1, 0, 1);
    public static final IntRange SMALL = new IntRange(-100, 100, 0, 10);
    public static final IntRange SMALL_NON_NEGATIVE = new IntRange(0, 100, 0, 10);

    public IntRange(int min, int max) {
        this(min, max, 0);
    }

    public IntRange(int min, int max, int defaultValue) {
        this(min, max, defaultValue, 10);
    }

    public IntRange(int min, int max, int defaultValue, float standardDeviation) {
        super(Integer.class);
        Check.greater(max, "max", min, "min");
        Check.normalNumber(standardDeviation, "standardDeviation");

        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        this.standardDeviation = standardDeviation;
    }

    @Override public Integer clampToRange(Integer originalValue) {
        notNull(originalValue, "originalValue");
        return MathUtils.clampToRange(originalValue, min, max);
    }

    @Override protected Integer createRandomValue(RandomSequence randomSequence) {
        return randomSequence.nextInt(min, max + 1);
    }

    @Override protected Integer doMutateValue(Integer value, float mutationAmount, RandomSequence randomSequence) {
        return (int)(value + mutationAmount * standardDeviation * randomSequence.nextGaussianFloat());
    }

    @Override protected Integer createCopy(Integer source) {
        return source;
    }

    @Override protected Integer createDefaultValue() {
        return defaultValue;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public float getStandardDeviation() {
        return standardDeviation;
    }

    @Override public Integer interpolate(double t, Integer a, Integer b, Integer out) {
        return (int) (1.0*a + t * (1.0*b - 1.0*a));
    }

}
