package com.github.chaosmelone9.datavisualizer.ui;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.components.WindowContent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {

    WindowContent windowContent;
    Main instance;

    public MainWindow(Main instance) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        super();
        this.instance = instance;
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setTitle("DataVisualizer");
        setLocationRelativeTo(null);
        setSize(1000,800);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        windowContent = new WindowContent(this);
        setRootPane(windowContent);
        setIconImage(ImageIO.read(instance.getFetcher().fetch("icon.png")));
        setVisible(true);
    }

    public WindowContent getWindowContent() {
        return windowContent;
    }

    public Main getInstance() {
        return instance;
    }
}
