package org.editflow.view.property

import org.editflow.view.View
import javax.swing.{JScrollPane, JComponent, JPanel}
import java.awt.FlowLayout
import net.miginfocom.swing.MigLayout
import org.editflow.propertyaccess.{Property, PropertyExtractor}
import org.editflow.EditorContext

/**
 *
 */
class BeanView(context: EditorContext) extends View {

  private var editors: List[PropertyEditor] = Nil

  protected def createComponent(): JComponent = {
    val panel = new JPanel(new MigLayout("wrap 1"))
    populateEditorUi(panel)
    panel
  }

  override protected def updateUi(doc: AnyRef, component: JComponent) {
    editors = Nil
    component.removeAll()
    populateEditorUi(component)
  }

  private def populateEditorUi(panel: JComponent) {

    if (doc != null) {

      val objectProperties: List[Property] = context.propertyExtractor.getObjectProperties(doc)

      objectProperties foreach {
        p =>
          val editor = context.editorFactory.createEditor(p)
          if (editor != null) {
            editors ::= editor
            panel.add(editor.getComponent)
          }
      }
    }
  }
}
