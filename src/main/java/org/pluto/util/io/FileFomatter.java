package org.pluto.util.io;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * ファイルを任意のフォーマットで読み込みます。
 */
public interface FileFomatter {
    ExceptionCatcherVoid IGNORED = e -> {};
    ExceptionCatcherVoid PRINT_STACKTREE = Throwable::printStackTrace;
    ExceptionCatcherVoid THROW = e -> {throw e;};
    ExceptionCatcher<?> RETURN_NULL = e -> null;

    InputStream asStream();

    BufferedImage asImage() throws IOException;

    String asText() throws IOException;

    byte[] asByteArray() throws IOException;

    default BufferedImage asImage(ExceptionCatcherVoid catcher) {
        try {
             return asImage();
        } catch (Exception e) {
            try {
                catcher.invoke(e);
            } catch (Exception ex) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    default BufferedImage asImage(ExceptionCatcher<BufferedImage> catcher) {
        try {
            return asImage();
        } catch (Exception e) {
            try {
                return catcher.ret(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    default String asText(ExceptionCatcherVoid catcher) {
        try {
            return asText();
        } catch (Exception e) {
            try {
                catcher.invoke(e);
            } catch (Exception ex) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    default String asText(ExceptionCatcher<String> catcher) {
        try {
            return asText();
        } catch (Exception e) {
            try {
                return catcher.ret(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    default byte[] asByteArray(ExceptionCatcherVoid catcher) {
        try {
            return asByteArray();
        } catch (Exception e) {
            try {
                catcher.invoke(e);
            } catch (Exception ex) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    default byte[] asByteArray(ExceptionCatcher<byte[]> catcher) {
        try {
            return asByteArray();
        } catch (Exception e) {
            try {
                return catcher.ret(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    interface ExceptionCatcherVoid {
        void invoke(Exception e) throws Exception;
    }

    interface ExceptionCatcher<T> {
        T ret(Exception e) throws Exception;
    }
}
