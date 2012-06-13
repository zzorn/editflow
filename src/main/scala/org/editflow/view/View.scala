package org.editflow.view

import org.editflow.document.Doc
import javax.swing.{JScrollPane, JComponent}

/**
 *
 */
trait View {

  type D <: Doc
  type C <: JComponent

  private var _doc: D = null.asInstanceOf[D]
  private var _component: C = null.asInstanceOf[C]
  private var viewComp: JComponent = null

  final def doc: D = _doc

  final def setDoc(doc: D) {
    _doc = doc
    if (_component != null) updateUi(_doc, _component)
  }

  final def component: JComponent = {
    if (_component == null) {
      _component = createComponent()

      if (wrapInScrollPane) viewComp = new JScrollPane(_component)
      else viewComp = _component
    }

    viewComp
  }

  protected def createComponent(): C

  protected def updateUi(doc: D, component: C) {}

  protected def wrapInScrollPane = true

}

