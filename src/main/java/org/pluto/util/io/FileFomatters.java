package org.pluto.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileFomatters {
    private FileFomatters() {}

    public static FileFomatter getFomatter(File path) throws FileNotFoundException {
        return new InputStreamFomatter(new FileInputStream(path));
    }
}
