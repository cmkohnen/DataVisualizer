package com.github.chaosmelone9.datavisualizer.dataStorage;

import java.io.File;
import java.nio.file.Files;

public class FileInteractor {
    public static String getStringFromFile(File file) throws Exception {
        return new String(Files.readAllBytes(file.toPath()));
    }
}
