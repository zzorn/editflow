package org.editflow.view.property

import org.editflow.view.View
import org.editflow.document.Doc
import javax.swing.{JScrollPane, JComponent, JPanel}
import java.awt.FlowLayout
import net.miginfocom.swing.MigLayout

/**
 *
 */
class PropertyView extends View {

  private var editors: List[PropertyEditor] = Nil

  type D = Doc
  type C = JPanel

  protected def createComponent(): C = {
    val panel = new JPanel(new MigLayout("wrap 1"))
    populateEditorUi(panel)
    panel
  }

  override protected def updateUi(doc: PropertyView#D, component: PropertyView#C) {
    editors = Nil
    component.removeAll()
    populateEditorUi(component)
  }

  private def populateEditorUi(panel: JPanel) {
    if (doc != null) {
      doc.properties foreach {
        p =>
          val editor = EditorFactory.createEditor(p)
          editors ::= editor
          panel.add(editor.component)
      }
    }
  }
}
