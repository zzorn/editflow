package org.editflow.view

import javax.swing.{JPanel, JComponent}
import org.editflow.EditorContext
import org.editflow.document.Doc


/**
 *
 */
class LibraryView(context: EditorContext) extends View {

  type D = Doc
  type C = JPanel

  protected def createComponent() = new JPanel()
}