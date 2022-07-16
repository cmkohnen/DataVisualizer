package com.github.chaosmelone9.datavisualizer;

import com.github.chaosmelone9.datavisualizer.config.Config;
import com.github.chaosmelone9.datavisualizer.resources.ResourceFetcher;
import com.github.chaosmelone9.datavisualizer.ui.windows.ErrorWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class Main {
    Config config;
    ResourceFetcher fetcher;
    Logger logger;
    public static void main(String[] args) {
        if(args.length > 0) {
            new Main(args);
        } else {
            new Main();
        }
    }

    public static void createNewGUIInstance() {
        new Main();
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
        this.fetcher = new ResourceFetcher();
        this.config = Config.init();
        this.logger = config.getLogger();
        try {
            initCLI(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Main() {
        this.fetcher = new ResourceFetcher();
        this.config = Config.init();
        this.logger = config.getLogger();
        try {
            //UIManager.setLookAndFeel("UIManager.getSystemLookAndFeelClassName()");
            initGUI();
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new ErrorWindow("Something went wrong", Optional.of(e));
        }
    }

    private void initGUI() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new MainWindow(this);
    }

    private void initCLI(String[] args) throws IOException {
        if(args[0].equals("--about")) {
            logger.echo(fetcher.fetchTextFromFile("about.txt"));
        }
    }
}
