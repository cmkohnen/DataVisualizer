package com.github.chaosmelone9.datavisualizer.ui;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.components.WindowContent;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(Main instance) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setTitle("DataVisualizer");
        setLocationRelativeTo(null);
        setSize(1000,800);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setRootPane(new WindowContent(this));
        setVisible(true);
    }
}
