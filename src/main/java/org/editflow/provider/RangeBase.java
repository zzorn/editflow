package org.editflow.provider;

import org.flowutils.random.RandomSequence;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.flowutils.Check.notNull;

/**
 * Common functionality for Ranges.
 */
public abstract class RangeBase<T> implements Range<T> {

    private final Class<T> type;

    private final NumberFormat numberFormat = new DecimalFormat("#0.0###");

    public RangeBase(Class<T> type) {
        notNull(type, "type");
        this.type = type;
    }

    @Override public final T randomValue(RandomSequence randomSequence) {
        return clampToRange(createRandomValue(randomSequence));
    }

    @Override public final T mutateValue(T value, float mutationAmount, RandomSequence randomSequence) {
        return clampToRange(doMutateValue(value, mutationAmount, randomSequence));
    }

    @Override public final T copy(T source) {
        return clampToRange(createCopy(source));
    }

    @Override public final T getDefaultValue() {
        return clampToRange(createDefaultValue());
    }

    @Override public final Class<T> getTypeClass() {
        return type;
    }

    @Override public String valueToString(T value) {
        if (value == null) return "null";
        else if (value instanceof Float ||
                 value instanceof Double) {
            return numberFormat.format(value);
        }
        else return value.toString();
    }

    @Override public final T interpolate(double t, T a, T b) {
        return interpolate(t, a, b, createDefaultValue());
    }

    protected abstract T createRandomValue(RandomSequence randomSequence);
    protected abstract T doMutateValue(T value, float mutationAmount, RandomSequence randomSequence);
    protected abstract T createCopy(T source);
    protected abstract T createDefaultValue();


}
