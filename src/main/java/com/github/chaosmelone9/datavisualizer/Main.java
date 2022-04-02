package com.github.chaosmelone9.datavisualizer;

import com.github.chaosmelone9.datavisualizer.config.Config;
import com.github.chaosmelone9.datavisualizer.resources.ResourceFetcher;
import com.github.chaosmelone9.datavisualizer.ui.MainWindow;

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
        if(args.length > 0) {
            if(args[0].equals("--about")) {
                try {
                    System.out.println(fetcher.fetchTextFromFile("about.txt"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            this.config = Config.init();
            try {
                window = new MainWindow(this);
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        }
    }
}
