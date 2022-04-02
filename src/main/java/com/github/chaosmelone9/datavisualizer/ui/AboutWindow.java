package com.github.chaosmelone9.datavisualizer.ui;

import javax.swing.*;
import java.io.IOException;

public class AboutWindow extends JDialog {
    public AboutWindow(MainWindow window) throws IOException {
        super(window,"about", true);
        setLocationRelativeTo(window);
        add(new JTextArea(window.getInstance().getFetcher().fetchTextFromFile("about.txt")));
        setSize((int) (window.getWidth() * .25), (int) (window.getHeight() * .25));
        setResizable(false);
        setVisible(true);
    }
}
