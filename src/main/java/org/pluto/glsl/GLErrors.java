package org.pluto.glsl;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.pluto.util.Immutable;
import org.pluto.util.ImmutableTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * C言語側でのglslエラーを処理する
 */
public final class GLErrors {
    @Immutable(target = ImmutableTarget.LENGTH)
    private static HashMap<Status, GLErrorCallback> callbacks;

    private static final GLErrorCallback NULL = c -> {};
    private static boolean checkNoErr = false;
    static List<Status> errors = new ArrayList<>();

    public static void init() {
        GLErrors.callbacks = new HashMap<>(Stream.of(Status.values())
                .collect(Collectors.toMap(s -> s, s -> s.callback)));
    }

    public static void checkWhenNoError(boolean doCheck) {
        checkNoErr = doCheck;
    }

    public static int check() {
        Status status = Status.of(GL11.glGetError());

        GLErrorCallback callback = callbacks.get(status);
        if (status != Status.NO_ERROR || checkNoErr) {
            callback.callback(status);
        }
        return status.code;
    }

    public static int check(Status err, GLErrorCallback callback) {
        Status status = Status.of(GL11.glGetError());

        if (status != Status.NO_ERROR || checkNoErr) {
            if (err == status) callback.callback(status);
        }
        return status.code;
    }

    private void checked(Status status) {
        errors.add(status);
    }

    public static void setErrorCallback(@NotNull Status err, GLErrorCallback callback) {
        Objects.requireNonNull(err);
        if (callback == null) callback = NULL;
        callbacks.put(err, callback);
    }

    public static void setErrorCallback(int err, GLErrorCallback callback) {
        Objects.requireNonNull(callback);
        Status key = Status.of(err);
        callbacks.put(key, callback);
    }

    public static final GLErrorCallback printError = s ->
            System.err.println(">> gl: " + s.name().replace("_", " ").toLowerCase());
    /**
     * @see GL11#GL_NO_ERROR
     * @see GL11#GL_INVALID_ENUM
     * @see GL11#GL_INVALID_VALUE
     * @see GL11#GL_INVALID_OPERATION
     * @see GL11#GL_STACK_OVERFLOW
     * @see GL11#GL_STACK_UNDERFLOW
     * @see GL11#GL_OUT_OF_MEMORY
     */
    public enum Status {
        NO_ERROR(GL11.GL_NO_ERROR, NULL),
        INVALID_ENUM(GL11.GL_INVALID_ENUM, NULL),
        INVALID_VALUE(GL11.GL_INVALID_VALUE, NULL),
        INVALID_OPERATION(GL11.GL_INVALID_OPERATION, NULL),
        STACK_OVERFLOW(GL11.GL_STACK_OVERFLOW, NULL),
        STACK_UNDERFLOW(GL11.GL_STACK_UNDERFLOW, NULL),
        OUT_OF_MEMORY(GL11.GL_OUT_OF_MEMORY, NULL),
        ;

        public static Status of(int errCode) {
            return switch (errCode) {
                case GL11.GL_NO_ERROR -> NO_ERROR;
                case GL11.GL_INVALID_ENUM -> INVALID_ENUM;
                case GL11.GL_INVALID_VALUE -> INVALID_VALUE;
                case GL11.GL_INVALID_OPERATION -> INVALID_OPERATION;
                case GL11.GL_STACK_OVERFLOW -> STACK_OVERFLOW;
                case GL11.GL_STACK_UNDERFLOW -> STACK_UNDERFLOW;
                case GL11.GL_OUT_OF_MEMORY -> OUT_OF_MEMORY;
                default -> throw new IllegalArgumentException("unkown err status");
            };
        }

        Status(int statusCode, GLErrorCallback callback) {
            code = statusCode;
            this.callback = callback;
        }

        public final int code;

        private final GLErrorCallback callback;
    }

    public interface GLErrorCallback {
        void callback(Status errcode);
    }
}