package org.editflow;

import org.flowutils.Check;
import org.flowutils.Symbol;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.flowutils.Check.notNull;

/**
 * Base class or delegate providing ObservableBean listener management.
 */
public class ObservableBeanBase implements ObservableBean {

    private final Set<BeanChangeListener> listeners = new CopyOnWriteArraySet<BeanChangeListener>();

    @Override public final void addBeanChangeListener(BeanChangeListener listener) {
        notNull(listener, "listener");

        listeners.add(listener);
    }

    @Override public void removeBeanChangeListener(BeanChangeListener listener) {
        listeners.remove(listener);
    }

    @Override public final void notifyBeanChanged() {
        notifyBeanChanged(this);
    }

    /**
     * Notifies listeners that the specified bean has changed substantially.
     */
    public final void notifyBeanChanged(Object bean) {
        Check.notNull(bean, "bean");

        for (BeanChangeListener listener : listeners) {
            listener.onBeanChanged(bean);
        }
    }

    @Override public final void notifyPropertyChanged(Symbol propertyId, Object oldValue, Object newValue) {
        notifyPropertyChanged(this, propertyId, oldValue, newValue);
    }

    /**
     * Notifies listeners that the specified bean property of the specified bean has changed.
     */
    public final void notifyPropertyChanged(Object bean, Symbol propertyId, Object oldValue, Object newValue) {
        Check.notNull(bean, "bean");
        Check.notNull(propertyId, "propertyId");

        // Notify listeners
        for (BeanChangeListener listener : listeners) {
            listener.onPropertyChanged(bean, propertyId, oldValue, newValue);
        }
    }



}
