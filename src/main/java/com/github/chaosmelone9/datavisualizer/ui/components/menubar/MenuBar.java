package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.MainWindow;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar(MainWindow window) {
        register(new FileMenu(window));
        register(new ViewMenu(window));
    }

    private void register(JMenu menu) {
        this.add(menu);
    }
}
