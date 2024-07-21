package org.pluto.util;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * The class granted this annotation behave immutable. But
 * when as below,not guarantee even if it granted this annotation.
 * <pre>{@code
 *     ImmutableClass a = b;
 * }</pre>
 * When return value, the value not rewrite field of origin.
 * Not guarantee perfect immutable.
 * <ul>
 *     <li>parameter - A parameter granted this annotation is not rewrite.</li>
 *     <li>type - A class is immutable.</li>
 *     <li>method - A return value of method is not linked this parameter of class.</li>
 * </ul>
 */
@Target({FIELD, PARAMETER, TYPE, METHOD})
public @interface Immutable {
    /**
     * This fields of name are must be immutable.
     * @return Field name of interest.
     */
    String[] field() default {}; //TODO

    ImmutableTarget[] target() default {};
}