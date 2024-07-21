package org.pluto.util.io;

public final class ResourceFomatters {
    private ResourceFomatters() {}

    public static FileFomatter getFomatter(String path) {
        return new InputStreamFomatter(ResourceFomatters.class.getClassLoader().getResourceAsStream(path));
    }
}
