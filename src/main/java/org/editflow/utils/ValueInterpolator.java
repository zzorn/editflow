package org.editflow.utils;

/**
 * Something that can interpolate between two values of the specified type.
 */
public interface ValueInterpolator<T> {

    /**
     * @param t 0 = return value of a, 1 = return value of b,
     *          0..1 = linearly interpolate between a and b and return the result in out and as return value.
     * @param a value for t value of 0.  This value should not be changed by this function if it is mutable.
     * @param b value for t value of 1.  This value should not be changed by this function if it is mutable.
     * @param out if T is a mutable type, the result of the interpolation should be written to this object, and this object returned as result.
     *            If T is not mutable (e.g. primitive numbers), this can be ignored.
     * @return the interpolated value (in the out object, if it is mutable).
     */
    T interpolate(double t, T a, T b, T out);

    /**
     * @param t 0 = return value of a, 1 = return value of b,
     *          0..1 = linearly interpolate between a and b and return the result.
     * @param a value for t value of 0.  This value should not be changed by this function if it is mutable.
     * @param b value for t value of 1.  This value should not be changed by this function if it is mutable.
     * @return the interpolated value.
     */
    T interpolate(double t, T a, T b);

}
