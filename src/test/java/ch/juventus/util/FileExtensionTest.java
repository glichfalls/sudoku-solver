package ch.juventus.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileExtensionTest {

    @Test
    public void testGetExtensionFromPath() {
        String path = "c:/test/path/file.png";
        assertEquals("png", FileExtension.getExtensionFromPath(path));
    }

    @Test
    public void testGetExtensionFromMultiExtensionPath() {
        String path = "c:/test/path/archive.tar.gz";
        assertEquals("gz", FileExtension.getExtensionFromPath(path));
    }

    @Test
    public void testGetNoExtensionFromPath() {
        String path = "c:/test";
        assertEquals("", FileExtension.getExtensionFromPath(path));
    }

}