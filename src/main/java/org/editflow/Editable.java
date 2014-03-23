package org.editflow;

import java.lang.annotation.*;

/**
 * Specifies the editor to use with the associated property.  Should be applied to the properties getter.
 * Defaults to a default editor for the property type if none specified.
 * Also optionally specifies parameters for the editor, as an array of key = value entries.
 *
 * Add this annotation to either fields or property getter methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Editable {

    /**
     * @return name of the editor to use to edit this property.
     */
    String editor() default "";

    /**
     * @return user readable name of the property.
     */
    String name() default "";

    /**
     * @return user readable description of the property.
     */
    String desc() default "";

    /**
     * @return parameters to pass to the editor.
     */
    String[] parameters() default {};

}
