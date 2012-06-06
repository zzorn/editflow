package org.editflow

import view.EditorMainView

/**
 *
 */
class Editor(context: EditorContext) {

  def main(args: Array[String]) {
    context.mainView.start()
  }



}