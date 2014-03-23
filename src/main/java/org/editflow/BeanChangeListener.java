package org.editflow;

import org.flowutils.Symbol;

/**
 * Listener that is notified when beans or their properties change.
 */
public interface BeanChangeListener {

    /**
     * Should be called when the whole bean changes, not just a single property.
     *
     * @param bean the bean that changed.
     */
    void onBeanChanged(Object bean);

    /**
     * Should be called when the value of a bean property changes.
     *
     * @param bean the bean that the changed property is in.
     * @param propertyId the identifier of the property that changed.
     * @param oldValue old value of the property
     * @param newValue new value of the property
     */
    void onPropertyChanged(Object bean, Symbol propertyId, Object oldValue, Object newValue);

}
