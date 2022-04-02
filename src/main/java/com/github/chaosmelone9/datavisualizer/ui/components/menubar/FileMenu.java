package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.MainWindow;

import javax.swing.*;

public class FileMenu extends JMenu {

    JMenuItem close;
    MainWindow window;

    public FileMenu(MainWindow window) {
        super("File");
        this.window = window;
        close = new JMenuItem("Close");
        close.addActionListener(actionEvent -> window.dispose());
        add(close);


    }
}
