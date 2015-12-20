package org.editflow.parameter;

import com.badlogic.gdx.utils.Array;
import org.demoflow.node.DemoNode;
import org.demoflow.node.DemoNodeBase;
import org.demoflow.parameter.calculator.CalculationContext;
import org.demoflow.parameter.calculator.Calculator;
import org.demoflow.parameter.range.Range;
import org.demoflow.utils.EmptyArray;
import org.flowutils.Symbol;

import java.util.ArrayList;

import static org.flowutils.Check.notNull;

/**
 * Implementation of Parameter.
 */
public final class VariableImpl<T> extends DemoNodeBase implements Variable<T> {

    private final Symbol id;
    private final boolean constant;
    private Range<T> range;
    private T value;
    private T defaultValue;
    private Calculator<T> calculator;
    private ArrayList<ParameterListener<T>> listeners = null;


    /**
     * @param id unique id for the parameter within the Parametrized class where it is.
     * @param host the Parametrized class that this parameter belongs to.
     * @param range allowed range for the parameter.
     */
    public VariableImpl(Symbol id, Parametrized host, Range<T> range) {
        this(id, host, range, range.getDefaultValue());
    }

    /**
     * @param id unique id for the parameter within the Parametrized class where it is.
     * @param host the Parametrized class that this parameter belongs to.
     * @param range allowed range for the parameter.
     * @param value initial value of the parameter.
     */
    public VariableImpl(Symbol id, Parametrized host, Range<T> range, T value) {
        this(id, host, range, value, false);
    }

    /**
     * @param id unique id for the parameter within the Parametrized class where it is.
     * @param host the Parametrized class that this parameter belongs to.
     * @param range allowed range for the parameter.
     * @param value initial value of the parameter.
     * @param constant if true, the parameter can not be changed over time.
     */
    public VariableImpl(Symbol id, Parametrized host, Range<T> range, T value, boolean constant) {
        this(id, host, range, value, constant, null, null);
    }

    /**
     * @param id unique id for the parameter within the Parametrized class where it is.
     * @param host the Parametrized class that this parameter belongs to.
     * @param range allowed range for the parameter.
     * @param value initial value of the parameter.
     * @param constant if true, the parameter can not be changed over time.
     * @param calculator calculator to use to calculate the parameter, or null if no calculator assigned initially.
     * @param listener listener that is notified when the parameter value is changed.
     */
    public VariableImpl(Symbol id,
                        Parametrized host,
                        Range<T> range,
                        T value,
                        boolean constant,
                        Calculator<T> calculator,
                        ParameterListener<T> listener) {
        super(host);

        notNull(id, "id");
        notNull(host, "host");
        notNull(range, "range");

        this.id = id;
        this.constant = constant;
        this.range = range;
        this.value = range.copy(value);
        this.defaultValue = range.copy(value);
        this.calculator = calculator;

        if (listener != null) {
            addParameterListener(listener);
        }
    }

    @Override public Symbol getId() {
        return id;
    }

    @Override public String getName() {
        return id.getString();
    }

    @Override public Parametrized getHost() {
        return (Parametrized) getParent();
    }

    @Override public T get() {
        return value;
    }

    @Override public void set(T newValue) {
        set(newValue, true);
    }

    @Override public void set(T newValue, boolean alsoSetInitialValue) {
        value = range.clampToRange(newValue);

        // Update initial value as well if requested
        if (alsoSetInitialValue) {
            defaultValue = range.copy(value);
        }

        // Notify listeners
        getHost().onParameterChanged(this, id, value);
        notifyValueChanged(newValue);
        if (alsoSetInitialValue) notifyDefaultValueChanged(newValue);
        notifyNodeUpdated();
    }

    @Override public Range<T> getRange() {
        return range;
    }

    @Override public Calculator<T> getCalculator() {
        return calculator;
    }

    @Override public <C extends Calculator<T>> C setCalculator(C calculator) {
        if (this.calculator != calculator) {
            if (this.calculator != null) {
                this.calculator.setParent(null);
                notifyChildNodeRemoved(this.calculator);
            }

            this.calculator = calculator;

            if (this.calculator != null) {
                this.calculator.setParent(this);
                notifyChildNodeAdded(this.calculator);
            }
            else {
                // Revert to previously manually set value when a calculator is removed
                set(defaultValue, false);
            }

            notifyNodeUpdated();
            notifyCalculatorChanged(this.calculator);
        }

        return calculator;
    }

    @Override public boolean isConstant() {
        return constant;
    }

    @Override public Class<T> getType() {
        return range.getType();
    }

    @Override public void recalculateParameter(CalculationContext calculationContext) {
        if (calculator != null) {
            // Set value without changing the initial value
            set(calculator.calculate(calculationContext, value, this), false);
        }
    }

    @Override public void resetToInitialValue() {
        // Revert to initial value (or manually specified value)
        set(range.copy(defaultValue), false);

        // Reset calculator state as well
        if (calculator != null) {
            calculator.resetState();
        }
    }

    @Override public int getChildCount() {
        return calculator != null ? calculator.getChildCount() : 0;
    }

    @Override public Array<? extends DemoNode> getChildren() {
        if (calculator != null) return calculator.getChildren();
        else return EmptyArray.EMPTY_ARRAY;
    }

    @Override public int getTotalNumberOfDescendants() {
        if (calculator != null) return  calculator.getTotalNumberOfDescendants();
        else return 0;
    }

    @Override public T getDefaultValue() {
        return defaultValue;
    }

    @Override public final void addParameterListener(ParameterListener<T> listener) {
        notNull(listener, "listener");
        if (listeners != null && listeners.contains(listener))
            throw new IllegalArgumentException(
                    "The ParameterListener<T> has already been added as a listener, can't add it twice");

        if (listeners == null) {
            listeners = new ArrayList<ParameterListener<T>>(4);
        }

        listeners.add(listener);
    }

    @Override public final void removeParameterListener(ParameterListener<T> listener) {
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    /**
     * Notifies about value change.
     */
    private void notifyValueChanged(T newValue) {
        if (listeners != null) {
            for (ParameterListener<T> listener : listeners) {
                listener.onChanged(this, newValue);
            }
        }
    }

    /**
     * Notifies about default value change.
     */
    private void notifyDefaultValueChanged(T newValue) {
        if (listeners != null) {
            for (ParameterListener<T> listener : listeners) {
                listener.onDefaultValueChanged(this, newValue);
            }
        }
    }

    private void notifyCalculatorChanged(Calculator<T> newValue) {
        if (listeners != null) {
            for (ParameterListener<T> listener : listeners) {
                listener.onCalculatorChanged(this, newValue);
            }
        }
    }

}
