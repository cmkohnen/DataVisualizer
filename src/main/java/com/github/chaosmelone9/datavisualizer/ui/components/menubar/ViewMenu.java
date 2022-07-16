package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.ContentPane;
import com.github.chaosmelone9.datavisualizer.ui.windows.GraphCustomizerWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class ViewMenu extends Menu {
    public ViewMenu(MainWindow window) {
        super(window, "View");

        add(new CheckBoxMenuItem("Show options menu", true, actionEvent -> window.toggleOptionPane(((JCheckBoxMenuItem) (actionEvent.getSource())).isSelected())));
        add(new MenuItem("Customize Graph...", actionEvent -> new GraphCustomizerWindow(window)));
        add(new MenuItem("Detach / Reattach Graph", actionEvent -> window.getContentPane().manageGraph()));
    }
}
