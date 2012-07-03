package org.editflow

import view.EditorMainView

/**
 *
 */
class Editor(val context: EditorContext) {

  def main(args: Array[String]) {
    context.mainView.start()
  }



}