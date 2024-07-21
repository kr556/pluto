package org.pluto.util;

/**
 * element of want to immutable.
 */
public enum ImmutableTarget {
    /**
     * Use to field for have length such as array, list, map...etc
     */
    LENGTH,

    /**
     * Use to value of have array nature. Target is not allowed change elements.
     */
    ELEMENT,
}
