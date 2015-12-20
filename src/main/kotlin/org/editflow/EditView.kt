package org.editflow

import net.miginfocom.swing.MigLayout
import javax.swing.JPanel
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty

/**
 * View over editable object, including editors to edit it.
 */
class EditView : JPanel(MigLayout()) {

    var editedObject: Any? = null
        set(value) {
            if (value !== field) {
                field = value
                rebuildUi(value)
            }
        }


    private fun rebuildUi(editedObject  : Any?) {
        removeAll()

        if (editedObject != null) {
            val c: KClass<Any> = editedObject.javaClass.kotlin
            for (member in c.members) {
                if (member is KMutableProperty) {
                    println("Edited object has property ${member.name} ")
                }
            }

        }

        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}