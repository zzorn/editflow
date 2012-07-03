package org.editflow.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the editor to use with the associated property.  Defaults to a default editor for the property type if none specified.
 * Also optionally specifies parameters for the editor, as an array of key = value entries.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Editable {
    String editor() default "";
    String[] parameters() default {};
}
