package ch.juventus.util;

import java.io.File;

public class FileExtension {

    public static String getExtensionFromFile(File file) {
        return getExtensionFromPath(file.getPath());
    }

    public static String getExtensionFromPath(String path) {
        int lastIndexOf = path.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return path.substring(lastIndexOf + 1);
    }

}
