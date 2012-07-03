package org.editflow.view.property

import org.editflow.propertyaccess.Property

/**
 *
 */
class EditorFactory {

  private var editorsByName = Map[String, () => PropertyEditor]()
  private var editorsByType = Map[Class[_], () => PropertyEditor]()

  registerEditor("DoubleEditor", classOf[Double], () => new NumberEditor())
  registerEditor("TextEditor",   classOf[String], () => new TextEditor())

  def registerEditor(name: String, editedType: Class[_], editor: () => PropertyEditor) {
    editorsByName += name -> editor
    editorsByType += editedType -> editor
  }

  def createEditor(property: Property): PropertyEditor = {
    val preferredEditor: String = property.editor

    val editor =
      if (preferredEditor != null)
        editorsByName.getOrElse(preferredEditor, null)
      else
        editorsByType.getOrElse(property.kind, null)

    if (editor == null) null
    else {
      val editorInstance = editor()
      editorInstance.setEditedProperty(property)
      editorInstance
    }
  }

}
