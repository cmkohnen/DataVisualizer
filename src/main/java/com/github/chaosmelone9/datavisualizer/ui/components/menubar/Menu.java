package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu extends JMenu {
    protected MainWindow window;

    public Menu(MainWindow window, String name) {
        super(name);
        this.window = window;
    }

    protected static class MenuItem extends JMenuItem {
        public MenuItem(String name, ActionListener actionListener) {
            super(name);
            addActionListener(actionListener);
        }
    }

    protected static class CheckBoxMenuItem extends JCheckBoxMenuItem {
        public CheckBoxMenuItem(String name, boolean checked, ActionListener actionListener) {
            super(name);
            setState(checked);
            addActionListener(actionListener);
        }
    }
}
