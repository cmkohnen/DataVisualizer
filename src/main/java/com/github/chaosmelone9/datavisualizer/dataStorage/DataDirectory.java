package com.github.chaosmelone9.datavisualizer.dataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class DataDirectory {
    private File directory = null;

    public void initDirectory() throws IOException {
        this.directory = new File(Paths.get("").toAbsolutePath().toString(), "DataVisualizer");
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Could not create DataVisualizer directory");
            }
        }
    }

    public DataDirectory(File directory) throws IOException {
        setDirectory(directory);
    }

    public DataDirectory() {
    }

    public File getDirectory() throws DataDirectoryNotInitialisedException {
        if(directory == null) {
            throw new DataDirectoryNotInitialisedException();
        }
        return directory;
    }

    public void setDirectory(File directory) throws FileNotFoundException {
        if (!this.directory.exists()) {
            throw new FileNotFoundException(String.format("Directory %s does not exist", directory));
        }
        this.directory = directory;
    }
}
