package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class FileMenu extends Menu {
    public FileMenu(MainWindow window) {
        super(window, "File");

        add(new MenuItem("New", actionEvent -> Main.createNewGUIInstance()));

        add(new MenuItem("Close", actionEvent -> window.dispose()));
    }
}
