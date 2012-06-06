package org.editflow

import view.{MainView, View, EditorMainView, LibraryView}


/**
 *
 */
case class DefaultEditorContext(applicationName: String,
                                useLightweightComponents: Boolean = true) extends EditorContext {


  lazy val mainView: MainView = new EditorMainView(this)

  lazy val libraryView: View = new LibraryView(this)

}