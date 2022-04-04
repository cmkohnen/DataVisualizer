package com.github.chaosmelone9.datavisualizer.config;

import java.io.File;

public class Config {
    public Config() {

    }

    public static Config init() {
        //TODO check for existing config or write one to location. Also check for args later
        return load(new File(""));
    }

    public static Config load(File configFile) {
        //TODO make config actually load
        return new Config();
    }
}
