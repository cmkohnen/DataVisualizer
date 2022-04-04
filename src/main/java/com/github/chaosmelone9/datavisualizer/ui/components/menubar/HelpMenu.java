package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.windows.AboutWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class HelpMenu extends JMenu {
    public HelpMenu(MainWindow window) {
        super("Help");
        JMenuItem about = new JMenuItem("about");
        add(about);
        about.addActionListener(actionEvent -> {
            try {
                new AboutWindow(window);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
