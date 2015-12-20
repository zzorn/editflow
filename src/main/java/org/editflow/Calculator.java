package org.editflow;

import org.editflow.provider.Type;

/**
 * Has zero or more parameters, and can calculate one result based on the parameters.
 * The parameters also have calculators.
 * The parameters can be edited interactively from an UI or specified programmatically.
 *
 *
 * The result of the calculation can also be this object itself (e.g. in the case of function type returns).
 *
 */
public interface Calculator<T> extends Module {

    /**
     * @return current value.
     *         If some of the parameters have changed since the current value was calculated, the current value may be recalculated.
     */
    T get();

    /**
     * @return type of the result produced by this calculator.
     */
    Type<T> getResultType();
}
