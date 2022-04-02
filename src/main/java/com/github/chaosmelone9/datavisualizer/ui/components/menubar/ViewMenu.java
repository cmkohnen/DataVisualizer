package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.MainWindow;

import javax.swing.*;

public class ViewMenu extends JMenu {
    public ViewMenu(MainWindow window) {
        super("View");

        JCheckBoxMenuItem toggleOptionPane = new JCheckBoxMenuItem("Show Options Menu");
        toggleOptionPane.setState(true);
        toggleOptionPane.addActionListener(actionEvent -> window.getWindowContent().toggleOptionPane(((AbstractButton) actionEvent.getSource()).getModel().isSelected()));

        add(toggleOptionPane);
    }
}
