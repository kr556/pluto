package org.pluto.util;

import org.linear.main.vector.Vector2d;

public class Size {
    private Vector2d value;

    public static Size of(double width, double height) {
        return new Size(width, height);
    }

    public static Size of(Vector2d size) {
        return new Size(size);
    }

    private Size() {}

    public Size(double width, double height) {
        value = new Vector2d(width, height);
    }

    public Size(Vector2d wh) {
        value = new Vector2d(wh);
    }

    public final double width() {
        return value.x;
    }

    public final double height() {
        return value.y;
    }

    private static UnsupportedOperationException immutableE() {
        return new UnsupportedOperationException("immutable size.");
    }

    public boolean isChangeable() {
        return false;
    }

    public void set(Vector2d wh) {
        throw immutableE();
    }

    public void set(double width, double height) {
        throw immutableE();
    }

    public Size toImmutable() {
        Size r = new Size();
        r.value = this.value;
        return r;
    }

    public Size toChangeable() {
        Size r =  new Size() {
            @Override
            public boolean isChangeable() {
                return true;
            }

            @Override
            public void set(Vector2d wh) {
                value.set(wh);
            }

            @Override
            public void set(double width, double height) {
                value.set(width, height);
            }
        };
        r.value = this.value;
        return r;
    }
}
