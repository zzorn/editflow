package org.editflow

import propertyaccess.{PropertyExtractor, Property}
import view.property.{BeanView, EditorFactory}
import view.{LibraryView, MainView, View}


/**
 *
 */
trait EditorContext {

  def applicationName: String

  def useLightweightComponents: Boolean

  def mainView: MainView

  def libraryView: LibraryView

  def beanView: BeanView

  def propertyExtractor: PropertyExtractor

  def editorFactory: EditorFactory

}