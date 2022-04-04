package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.windows.GraphCustomizerWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class ViewMenu extends JMenu {
    public ViewMenu(MainWindow window) {
        super("View");

        JCheckBoxMenuItem toggleOptionPane = new JCheckBoxMenuItem("Show Options Menu");
        toggleOptionPane.setState(true);
        toggleOptionPane.addActionListener(actionEvent -> window.getWindowContent().toggleOptionPane(((AbstractButton) actionEvent.getSource()).getModel().isSelected()));

        JMenuItem invokeGraphCustomizer = new JMenuItem("Customize Graph...");
        invokeGraphCustomizer.addActionListener(actionEvent -> new GraphCustomizerWindow(window));

        add(toggleOptionPane);
        add(invokeGraphCustomizer);
    }
}
