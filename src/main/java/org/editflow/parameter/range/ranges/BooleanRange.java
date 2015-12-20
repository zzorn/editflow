package org.editflow.parameter.range.ranges;

import org.demoflow.parameter.range.RangeBase;
import org.flowutils.Check;
import org.flowutils.MathUtils;
import org.flowutils.random.RandomSequence;

import static org.flowutils.Check.notNull;

/**
 * Range for boolean values.
 */
public final class BooleanRange extends RangeBase<Boolean> {

    private final boolean defaultValue;
    private final double probability;

    public static final BooleanRange FULL = new BooleanRange();
    public static final BooleanRange DEFAULT_TO_FALSE = new BooleanRange(false);
    public static final BooleanRange DEFAULT_TO_TRUE = new BooleanRange(true);

    public BooleanRange() {
        this(false);
    }

    public BooleanRange(boolean defaultValue) {
        this(defaultValue, 0.5);
    }

    public BooleanRange(double probability) {
        this(probability >= 0.5, probability);
    }

    public BooleanRange(boolean defaultValue, double probability) {
        super(Boolean.class);
        Check.normalNumber(probability, "probability");

        this.probability = MathUtils.clamp0To1(probability);
        this.defaultValue = defaultValue;
    }

    @Override protected Boolean createRandomValue(RandomSequence randomSequence) {
        return randomSequence.nextBoolean(probability);
    }

    @Override protected Boolean doMutateValue(Boolean value, float mutationAmount, RandomSequence randomSequence) {
        // Use mutation amount as the probability that we will randomize a new value.
        if (randomSequence.nextBoolean(mutationAmount)) {
            return randomSequence.nextBoolean(probability);
        }
        else {
            return value;
        }
    }

    @Override protected Boolean createCopy(Boolean source) {
        return source;
    }

    @Override protected Boolean createDefaultValue() {
        return defaultValue;
    }

    @Override public Boolean clampToRange(Boolean originalValue) {
        notNull(originalValue, "originalValue");
        return originalValue;
    }

    @Override public Boolean interpolate(double t, Boolean a, Boolean b, Boolean out) {
        return t < 0.5 ? a : b;
    }
}
