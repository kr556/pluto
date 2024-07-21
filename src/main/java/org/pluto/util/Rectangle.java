package org.pluto.util;

import org.linear.main.vector.Vector4d;

public class Rectangle {
    private Vector4d value;

    private Rectangle() {}

    public Rectangle(Vector4d xywh) {
        value = new Vector4d(xywh);
    }

    public Rectangle(double x, double y, double width, double height) {
        value = new Vector4d(x, y, width, height);
    }

    public final double x() {
        return value.x;
    }

    public final double y() {
        return value.y;
    }

    public final double width() {
        return value.z;
    }

    public final Position getPos() {
        return Position.of(x(), y());
    }

    public final Size getSize() {
        return Size.of(width(), height());
    }

    public final double height() {
        return value.w;
    }

    public Rectangle toImmutable() {
        Rectangle r = new Rectangle();
        r.value = this.value;
        return r;
    }

    /**
     *
     * @return
     */
    public Rectangle toChangeable() {
        Rectangle r =  new Rectangle() {
            @Override
            public boolean isChangeable() {
                return true;
            }

            @Override
            public void set(Vector4d xywh) {
                value.set(xywh);
            }

            @Override
            public void set(double x, double y, double width, double height) {
                value.set(x, y, width, height);
            }

            public void setX(double x) {
                value.x = x;
            }

            public void setPos(Position pos) {
                value.x = pos.x();
                value.y = pos.y();
            }

            public void setSize(Size size) {
                value.z = size.width();
                value.w = size.height();
            }

            public void setY(double y) {
                value.y = y;
            }

            public void setWidth(double width) {
                value.z = width;
            }

            public void setHeight(double height) {
                value.w = height;
            }
        };
        r.value = this.value;
        return r;
    }

    private static UnsupportedOperationException immutableE() {
        return new UnsupportedOperationException("immutable rectangle.");
    }

    public boolean isChangeable() {
        return false;
    }

    public void set(Vector4d xywh) {
        throw immutableE();
    }

    public void set(double x, double y, double width, double height) {
        throw immutableE();
    }

    public void setPos(Position pos) {
        throw immutableE();
    }

    public void setSize(Size size) {
        throw immutableE();
    }

    public void setX(double x) {
        throw immutableE();
    }

    public void setY(double y) {
        throw immutableE();
    }

    public void setWidth(double width) {
        throw immutableE();
    }

    public void setHeight(double height) {
        throw immutableE();
    }
}