package org.editflow.view.property

import javax.swing.JComponent
import org.editflow.propertyaccess.Property

/**
 *
 */
trait PropertyEditor {

  private var _editedProperty: Property = null
  private var _editorComponent: EditorType = null.asInstanceOf[EditorType]

  private var updatesOn = false

  type EditorType <: JComponent

  final def getComponent: JComponent = {
    if (_editorComponent == null) {
      _editorComponent = createEditor()

      // Initialize new editor with property
      if (_editedProperty != null) {
        updatesOn = false
        onEditedPropertyChanged(_editorComponent, _editedProperty)
        updatesOn = true
        updateUiFromValue()
      }
      else {
        updatesOn = false
        onEditedPropertyCleared(_editorComponent)
      }
    }

    _editorComponent
  }

  final def setEditedProperty(property: Property) {
    if (property == null) {
      _editedProperty = null

      if (_editorComponent != null) {
        updatesOn = false
        onEditedPropertyCleared(_editorComponent)
      }
    }
    else if (supportedType.isAssignableFrom(property.kind)) {
      _editedProperty = property

      if (_editorComponent != null) {
        updatesOn = false
        onEditedPropertyChanged(_editorComponent, _editedProperty)
        updatesOn = true
        updateUiFromValue()
      }
    }
    else throw new Error("Unsupported property type, this editor only supports properties with type '"+supportedType+"', " +
                         "but attempted to edit property with type '"+property.kind+"'")

  }

  final def getEditedProperty = _editedProperty

  def supportedType: Class[_]

  final def updateUiFromValue() {
    if (_editedProperty != null &&
      _editorComponent != null && updatesOn) {

      updatesOn = false
      setUiValue(_editorComponent, getValue)
      updatesOn = true
    }
  }

  final def updateValueFromUi() {
    if (_editedProperty != null &&
      _editorComponent != null && updatesOn) {

      updatesOn = false
      val value = getUiValue(_editorComponent)
      setValue(value)
      updatesOn = true
    }
  }

  protected def createEditor(): EditorType
  protected def onEditedPropertyChanged(component: EditorType, property: Property)
  protected def onEditedPropertyCleared(component: EditorType)

  protected def setUiValue(component: EditorType, value: AnyRef)
  protected def getUiValue(component: EditorType): AnyRef


  protected def setValue(value: AnyRef) {
    println("Setting value to " + value)
    if (_editedProperty != null) _editedProperty.setValue(value)
  }

  protected def getValue: AnyRef = {
    if (_editedProperty != null) _editedProperty.getValue
    else null
  }

}
