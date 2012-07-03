package org.editflow.propertyaccess

/**
 * Accessor for getting meta information about an objects property, as well as changing it.
 */
trait Property {

  def host: AnyRef

  def name: String

  def kind: Class[_]

  def isMutable: Boolean

  def getValue: AnyRef
  def setValue(value: AnyRef)

  /**
   * @return name of the editor to use, specified in annotations or similar.  Null if no specific editor specified.
   */
  def editor: String

  /**
   * @return parameters specified for the editor in annotations etc.  Empty map if none.
   */
  def editorParameters: Map[String, String]

}
