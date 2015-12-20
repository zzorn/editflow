package org.editflow.provider;


import org.editflow.utils.ValueInterpolator;
import org.flowutils.random.RandomSequence;

/**
 * Holds information on the allowed range for a parameter value.
 */
public interface Range<T> extends ValueInterpolator<T> {

    /**
     * @return original value clamped to a valid range for this parameter.
     * In case of mutable values, the provided valueToClamp is changed.
     */
    T clampToRange(T valueToClamp);

    /**
     * @return a random value in the range.
     */
    T randomValue(RandomSequence randomSequence);

    /**
     * Returns a slightly changed value.
     * In case of mutable values, the provided valueToMutate is changed.
     * @param valueToMutate
     * @param mutationAmount amount to mutate, 0 = no mutation, 1 = 100% + or -.
     * @param randomSequence
     * @return
     */
    T mutateValue(T valueToMutate, float mutationAmount, RandomSequence randomSequence);

    /**
     * Creates an unique copy of the source value, in case of non immutable parameter values.
     */
    T copy(T source);


}
