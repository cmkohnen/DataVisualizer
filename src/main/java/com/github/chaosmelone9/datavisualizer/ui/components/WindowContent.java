package com.github.chaosmelone9.datavisualizer.ui.components;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.MainWindow;
import com.github.chaosmelone9.datavisualizer.ui.components.menubar.MenuBar;

import javax.swing.*;

public class WindowContent extends JRootPane {
    public WindowContent(MainWindow window) {
        setJMenuBar(new MenuBar(window));
    }
}
