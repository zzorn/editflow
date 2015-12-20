package org.editflow;

/**
 *
 */
public abstract class CalculatorBase<T> implements Calculator<T> {


    @Override public T get() {
        // IMPLEMENT: Implement get
        return null;
    }

    /**
     * @return recalculate the value of this calculator using the current values of its parameters.
     */
    protected abstract T calculate();
}
