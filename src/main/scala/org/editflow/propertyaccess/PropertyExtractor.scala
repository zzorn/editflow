package org.editflow.propertyaccess

/**
 * Extracts properties from any object.
 */
trait PropertyExtractor {

  /**
   * @return a list of properties that can be used to access information about an objects properties, as well as reading and changing its value.
   */
  def getObjectProperties(thing: AnyRef): List[Property]

}
