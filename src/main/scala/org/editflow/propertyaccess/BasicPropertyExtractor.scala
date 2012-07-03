package org.editflow.propertyaccess

import java.lang.reflect.{Modifier, Method}
import org.scalastuff.scalabeans.Preamble._
import org.scalastuff.scalabeans.{PropertyDescriptor, BeanDescriptor}

/**
 *
 */
class BasicPropertyExtractor extends PropertyExtractor {

  def getObjectProperties(thing: AnyRef): List[Property] = {

    // TODO: Cache if needed

    val beanDescriptor: BeanDescriptor = descriptorOf(thing.getClass)

    (beanDescriptor.properties map {p => new BasicProperty(thing, p)}).toList
  }


}
