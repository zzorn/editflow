package org.editflow.editors;

import org.editflow.utils.Configuration;

/**
 * An editor for some type of value.
 */
public interface Editor<T> extends UiComponent {

    /**
     * @param configuration Configuration to use with this editor.
     */
    void setConfiguration(Configuration configuration);

    /**
     * @return existing configuration used by this editor.
     */
    Configuration getConfiguration();

    /**
     * Set value to edit, or notify editor about a change in the value object.
     */
    void setEditedValue(T value);

    /**
     * @return edited value.
     */
    T getEditedValue();

    /**
     * Should be called when the value is updated outside the editor.
     */
    void onValueUpdated();
}
