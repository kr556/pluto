package org.pluto.glsl;

import org.lwjgl.opengl.GL11;

import java.util.Locale;

import static org.lwjgl.opengl.GL11.*;

public final class GLEnums {
    /**
     * 描画のモードを列挙したものです。
     * @see org.lwjgl.opengl.GL11#GL_POINTS
     * @see org.lwjgl.opengl.GL11#GL_LINES
     * @see org.lwjgl.opengl.GL11#GL_LINE_LOOP
     * @see org.lwjgl.opengl.GL11#GL_LINE_STRIP
     * @see org.lwjgl.opengl.GL11#GL_TRIANGLES
     * @see org.lwjgl.opengl.GL11#GL_TRIANGLE_STRIP
     * @see org.lwjgl.opengl.GL11#GL_TRIANGLE_FAN
     * @see org.lwjgl.opengl.GL11#GL_QUADS
     * @see org.lwjgl.opengl.GL11#GL_QUAD_STRIP
     * @see org.lwjgl.opengl.GL11#GL_POLYGON
     */
    public enum DrawMode {
        POINTS(GL11.GL_POINTS),
        LINES(GL11.GL_LINES),
        LINE_LOOP(GL11.GL_LINE_LOOP),
        LINE_STRIP(GL11.GL_LINE_STRIP),
        TRIANGLES(GL11.GL_TRIANGLES),
        TRIANGLE_STRIP(GL11.GL_TRIANGLE_STRIP),
        TRIANGLE_FAN(GL11.GL_TRIANGLE_FAN),
        QUADS(GL11.GL_QUADS),
        QUAD_STRIP(GL11.GL_QUAD_STRIP),
        POLYGON(GL11.GL_POLYGON),
        ;

        private final int drawmode;

        DrawMode(int drawModeId) {
            this.drawmode = drawModeId;
        }

        public int getDrawmodeID() {
            return drawmode;
        }
    }

    /**
     * glslでの型を表します。<br>
     */
    public enum GLSLType {
        INT(1, GL11.GL_INT, "int"),
        FLOAT(1, GL11.GL_FLOAT, "float"),
        VEC2(2, GL11.GL_FLOAT, "vec2"),
        VEC3(3, GL11.GL_FLOAT, "vec3"),
        VEC4(4, GL11.GL_FLOAT, "vec4"),
        IVEC2(2, GL11.GL_INT, "ivec2"),
        IVEC3(3, GL11.GL_INT, "ivec3"),
        IVEC4(4, GL11.GL_INT, "ivec4"),
        MAT2(4, GL11.GL_FLOAT, "mat2"),
        MAT3(9, GL11.GL_FLOAT, "mat3"),
        MAT4(16, GL11.GL_FLOAT, "mat4"),
        SAMPLER2D(0, GL11.GL_NONE, "sampler2D"),
        ;

        /**
         * 名前を元に要素を返します．
         * @param name 大文字小文字は区別しません．
         * @return 名前に対応する要素を返します．
         */
        public static GLSLType of(String name) {
            return valueOf(name.toUpperCase());
        }

        /**
         * 要素の数です。
         */
        public final int size;

        /**
         * GL11での型のid。
         * @see GL11#GL_FLOAT
         * @see GL11#GL_INT
         */
        public final int type;

        /**
         * 型名。すべて小文字表記です。
         */
        public final String name;

        GLSLType(int size, int type, String name) {
            this.size = size;
            this.type = type;
            this.name = name;
        }
    }

}
