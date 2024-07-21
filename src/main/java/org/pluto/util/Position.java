package org.pluto.util;

import org.linear.main.vector.Vector2d;

public class Position {
    private Vector2d value;

    private Position() {}

    public Position(double x, double y) {
        value = new Vector2d(x, y);
    }

    public Position(Vector2d xy) {
        value = new Vector2d(xy);
    }

    public static Position of(double x, double y) {
        return new Position(x, y);
    }

    public static Position of(Vector2d pos) {
        return new Position(pos);
    }

    public final double x() {
        return value.x;
    }

    public final double y() {
        return value.y;
    }

    private static UnsupportedOperationException immutableE() {
        return new UnsupportedOperationException("immutable position.");
    }

    public boolean isChangeable() {
        return false;
    }

    public void set(Vector2d xy) {
        throw immutableE();
    }

    public void set(double x, double y) {
        throw immutableE();
    }

    public Position toImmutable() {
        Position r = new Position();
        r.value = this.value;
        return r;
    }

    public Position toChangeable() {
        Position r =  new Position() {
            @Override
            public boolean isChangeable() {
                return true;
            }

            @Override
            public void set(Vector2d xy) {
                value.set(xy);
            }

            @Override
            public void set(double x, double y) {
                value.set(x, y);
            }
        };
        r.value = this.value;
        return r;
    }
}
