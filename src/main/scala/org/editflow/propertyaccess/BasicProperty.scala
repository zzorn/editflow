package org.editflow.propertyaccess

import java.lang.reflect.Method
import org.editflow.utils.StringUtils
import org.scalastuff.scalabeans.{MutablePropertyDescriptor, PropertyDescriptor}
import org.editflow.annotations.Editable


class BasicProperty(val host: AnyRef, propertyDescriptor: PropertyDescriptor) extends Property {

  def name = propertyDescriptor.name

  def kind = propertyDescriptor.scalaType.erasure

  def editor = propertyDescriptor.findAnnotation[Editable] match {
    case None => null
    case Some(editor) => editor.editor()
  }

  def isMutable: Boolean = propertyDescriptor.mutable

  def editorParameters = propertyDescriptor.findAnnotation[Editable] match {
    case None => Map()
    case Some(editor) =>
      var params = Map[String, String]()
      editor.parameters() foreach { entry =>
        params += StringUtils.getBefore(entry, "=").trim -> StringUtils.getAfter(entry, "=").trim
      }
      params
  }

  def getValue: AnyRef = propertyDescriptor.get(host)

  def setValue(value: AnyRef) {
    propertyDescriptor match {
      case m: MutablePropertyDescriptor => m.set(host, value)
      case _ => throw new Error("Property " + name + " is not mutable")
    }
  }



}
