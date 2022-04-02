package com.github.chaosmelone9.datavisualizer;

import com.github.chaosmelone9.datavisualizer.config.Config;
import com.github.chaosmelone9.datavisualizer.ui.MainWindow;

public class Main {
    Config config;
    public static void main(String[] args) {
        new Main();
    }

    public Config getConfig() {
        return this.config;
    }

    public Main() {
        System.out.println("Hello World!");
        this.config = Config.init();
        try {
            MainWindow window = new MainWindow(this);
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
