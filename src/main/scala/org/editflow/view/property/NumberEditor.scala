package org.editflow.view.property

import org.editflow.propertyaccess.Property
import javax.swing.{AbstractAction, JTextField, JEditorPane, JPanel}
import java.awt.event.ActionEvent
import javax.swing.event.{DocumentEvent, DocumentListener}

/**
 *
 */
class NumberEditor extends PropertyEditor {
  type EditorType = JTextField

  def supportedType = classOf[Double]

  protected def createEditor() = {
    val field = new JTextField(20)

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

  protected def setUiValue(component: NumberEditor#EditorType, value: AnyRef) {
    component.setText("" + value.toString.toDouble)
  }

  protected def getUiValue(component: NumberEditor#EditorType): AnyRef = {
    try {
      java.lang.Double.valueOf(component.getText)
    }
    catch {
      case e: NumberFormatException =>
      // Ignore for now.. later use real number field
      java.lang.Double.valueOf(0.0)
    }
  }
}
