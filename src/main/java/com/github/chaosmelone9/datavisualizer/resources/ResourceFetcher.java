package com.github.chaosmelone9.datavisualizer.resources;

import java.io.IOException;
import java.io.InputStream;

public class ResourceFetcher {
    public InputStream fetch(String file) {
        return getClass().getClassLoader().getResourceAsStream(file);
    }

    public String fetchTextFromFile(String file) throws IOException {
        InputStream stream = fetch(file);
        StringBuilder output = new StringBuilder();
        int content;
        while ((content = stream.read()) != -1) {
            output.append((char)content);
        }
        return output.toString();
    }
}
