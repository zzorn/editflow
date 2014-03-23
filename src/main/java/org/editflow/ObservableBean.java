package org.editflow;

import org.flowutils.Symbol;

/**
 * Interface for beans that provide change listener support.
 */
public interface ObservableBean {

    /**
     * @param listener a listener that should be notified about changes to the bean or its properties.
     */
    void addBeanChangeListener(BeanChangeListener listener);

    /**
     * @param listener a listener that should be removed.
     */
    void removeBeanChangeListener(BeanChangeListener listener);

    /**
     * Notifies listeners that this bean has changed substantially.
     */
    void notifyBeanChanged();

    /**
     * Notifies listeners that the specified bean property of the this bean has changed.
     */
    void notifyPropertyChanged(Symbol propertyId, Object oldValue, Object newValue);
}
