package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class FileMenu extends JMenu {

    MainWindow window;

    public FileMenu(MainWindow window) {
        super("File");
        this.window = window;

        JMenuItem newInstance = new JMenuItem("New");
        newInstance.addActionListener(actionEvent -> Main.createNewGUIInstance());
        add(newInstance);

        JMenuItem close = new JMenuItem("Close");
        close.addActionListener(actionEvent -> window.dispose());
        add(close);
    }
}
