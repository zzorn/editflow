package org.editflow.editors;

import javax.swing.*;

/**
 * Base class for UI components that implements lazy creation of swing UI components.
 */
public abstract class UiComponentBase implements UiComponent {

    private JComponent ui;

    @Override public final JComponent getUi() {
        // Create if needed
        if (ui == null) {
            ui = createUi();

            // Sanity check
            if (ui == null) {
                throw new IllegalStateException("Could not create UI component " + getClass().getName());
            }
        }

        return ui;
    }

    /**
     * @return create the UI component.
     *         Only called if the UI component has not been created yet.
     */
    protected abstract JComponent createUi();


}
