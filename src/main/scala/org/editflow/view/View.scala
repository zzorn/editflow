package org.editflow.view

import javax.swing.{JScrollPane, JComponent}

/**
 *
 */
trait View {

  private var _doc: AnyRef = null
  private var _component: JComponent = null
  private var viewComp: JComponent = null

  final def doc: AnyRef = _doc

  final def setDoc(doc: AnyRef) {
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

  protected def createComponent(): JComponent

  protected def updateUi(doc: AnyRef, component: JComponent) {}

  protected def wrapInScrollPane = true

}

