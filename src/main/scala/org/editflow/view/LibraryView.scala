package org.editflow.view

import javax.swing.{JPanel, JComponent}
import org.editflow.EditorContext


/**
 *
 */
class LibraryView(context: EditorContext) extends View {

  private val panel = new JPanel()

  def component: JComponent = panel


}