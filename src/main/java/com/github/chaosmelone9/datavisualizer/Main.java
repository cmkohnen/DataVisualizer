package com.github.chaosmelone9.datavisualizer;

import com.github.chaosmelone9.datavisualizer.config.Config;
import com.github.chaosmelone9.datavisualizer.dataStorage.DataDirectory;
import com.github.chaosmelone9.datavisualizer.resources.ResourceFetcher;
import com.github.chaosmelone9.datavisualizer.ui.windows.ErrorWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    private final DataDirectory dataDirectory;
    private Config config;
    private final ResourceFetcher fetcher;
    private Logger logger;
    public static void main(String[] args) {
        new Main(args);
    }

    public static void createNewGUIInstance() {
        new Main(new String[]{});
    }

    public Config getConfig() {
        return this.config;
    }

    public ResourceFetcher getFetcher() {
        return fetcher;
    }

    public Logger getLogger() {
        return logger;
    }

    private Main(String[] args) {
        this.dataDirectory = new DataDirectory();
        this.fetcher = new ResourceFetcher();
        if(args.length > 0) {
            try {
                initCLI(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                //UIManager.setLookAndFeel("UIManager.getSystemLookAndFeelClassName()");
                this.dataDirectory.initDirectory();
                this.config = Config.load(new File(dataDirectory.getDirectory(), "config.yml"));
                this.logger = config.getLogger();
                initGUI();
            } catch (Exception e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ignored) {}
                new ErrorWindow("Something went wrong", Optional.of(e));
            }
        }

    }

    private void initGUI() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new MainWindow(this);
    }

    private void initCLI(String[] args) throws Exception {
        if(args[0].equals("--about")) {
            logger.echo(fetcher.fetchTextFromFile("about.txt"));
        }
    }
}
