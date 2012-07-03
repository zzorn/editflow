package org.editflow.examples

import org.editflow.annotations.Editable
import org.editflow.{DefaultEditorContext, Editor}
import java.awt.Color


/**
 *
 */
object Example1 extends Editor(new DefaultEditorContext("Test app")) {

  val cylinder = Cylinder()

  context.beanView.setDoc(cylinder)

}


case class Cylinder(var name: String = "cylinder",
                    var length: Double = 2,
                    radius: Double = 1,
                    var color: Color = Color.GRAY)