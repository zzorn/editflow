package org.editflow

import propertyaccess.{BasicPropertyExtractor, PropertyExtractor}
import view.property.{BeanView, EditorFactory}
import view.{MainView, View, EditorMainView, LibraryView}


/**
 *
 */
class DefaultEditorContext(val applicationName: String,
                           val useLightweightComponents: Boolean = true) extends EditorContext {


  lazy val mainView: MainView = new EditorMainView(this)

  lazy val libraryView: LibraryView = new LibraryView(this)

  lazy val beanView: BeanView = new BeanView(this)

  lazy val propertyExtractor: PropertyExtractor = new BasicPropertyExtractor()

  lazy val editorFactory: EditorFactory = new EditorFactory()

}