package org.editflow

import view.{MainView, View}


/**
 *
 */
trait EditorContext {

  def applicationName: String
  def useLightweightComponents: Boolean

  def mainView: MainView

  def libraryView: View

}