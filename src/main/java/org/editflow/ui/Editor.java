package org.editflow.ui;

import javax.swing.*;

/**
 *
 */
public interface Editor {

    /**
     * @return swing ui component with the editor.
     */
    JComponent getUi();

    /**
     * @param editedObject the object to edit, or null to empty editor.
     */
    void setEditedObject(Object editedObject);

    /**
     * @return currently edited object, or null if none.
     */
    Object getEditedObject();

    /**
     * Used to manually notify the editor that some properties of the object may have updated.
     *
     * If the object implements ObservableBean and sends notifications for updates, calling this is not needed.
     */
    void notifyObjectUpdated();


}
