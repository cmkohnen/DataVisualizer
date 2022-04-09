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
    MainWindow window;
    public static void main(String[] args) {
        new Main(args);
    }

    public Config getConfig() {
        return this.config;
    }

    public ResourceFetcher getFetcher() {
        return fetcher;
    }

    private Main(String[] args) {
        this.fetcher = new ResourceFetcher();
        this.config = Config.init();
        if(args.length > 0) {
            initCLI(args);
        } else {
            try {
                initGUI();
            } catch (Exception e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ignored) {}
                new ErrorWindow("Something went wrong", Optional.of(e));
            }
        }
    }

    private void initGUI() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        window = new MainWindow(this);
    }

    private void initCLI(String[] args) {
        if(args[0].equals("--about")) {
            try {
                //TODO implement Logger and forward this
                System.out.println(fetcher.fetchTextFromFile("about.txt"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
