package org.editflow.parameter.range.ranges;

import org.demoflow.field.Field;
import org.demoflow.parameter.range.RangeBase;
import org.flowutils.random.RandomSequence;

/**
 *
 */
public final class FieldRange extends RangeBase<Field> {

    public static final FieldRange FULL = new FieldRange();

    public FieldRange() {
        super(Field.class);
    }

    @Override protected Field createRandomValue(RandomSequence randomSequence) {
        throw new UnsupportedOperationException("This operation is not supported for Fields");
    }

    @Override protected Field doMutateValue(Field value, float mutationAmount, RandomSequence randomSequence) {
        throw new UnsupportedOperationException("This operation is not supported for Fields");
    }

    @Override protected Field createCopy(Field source) {
        throw new UnsupportedOperationException("This operation is not supported for Fields");
    }

    @Override protected Field createDefaultValue() {
        throw new UnsupportedOperationException("This operation is not supported for Fields");
    }

    @Override public Field clampToRange(Field originalValue) {
        // No support for ranges, return source
        return originalValue;
    }

    @Override public Field interpolate(double t, Field a, Field b, Field out) {
        throw new UnsupportedOperationException("This operation is not supported for Fields");
    }
}
