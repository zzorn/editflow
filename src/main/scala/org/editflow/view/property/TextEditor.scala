package org.editflow.view.property

import org.editflow.propertyaccess.Property
import javax.swing.{AbstractAction, JTextField}
import java.awt.event.ActionEvent
import javax.swing.event.{DocumentEvent, DocumentListener}

/**
 *
 */
class TextEditor extends PropertyEditor {

  type EditorType = JTextField

  def supportedType = classOf[String]

  protected def createEditor() = {
    val field = new JTextField(30)

    field.getDocument.addDocumentListener(new DocumentListener {
      def changedUpdate(e: DocumentEvent) {
        updateValueFromUi()
      }
      def removeUpdate(e: DocumentEvent) {
        updateValueFromUi()
      }
      def insertUpdate(e: DocumentEvent) {
        updateValueFromUi()
      }
    })

    field
  }

  protected def onEditedPropertyChanged(component: TextEditor#EditorType, property: Property) {
    component.setEditable(property.isMutable)
  }

  protected def onEditedPropertyCleared(component: TextEditor#EditorType) {
    component.setText("")
    component.setEditable(false)
  }

  protected def setUiValue(component: TextEditor#EditorType, value: AnyRef) {
    component.setText(value.toString)
  }

  protected def getUiValue(component: TextEditor#EditorType): AnyRef = {
    component.getText
  }
}
