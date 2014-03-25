package org.editflow.editors;

/**
 *
 */
public interface EditorListener<T> {

    void onValueEdited(Editor editor, T newValue);

}
