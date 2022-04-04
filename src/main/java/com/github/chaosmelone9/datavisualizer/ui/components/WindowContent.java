package com.github.chaosmelone9.datavisualizer.ui.components;

import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.ContentPane;
import com.github.chaosmelone9.datavisualizer.ui.components.menubar.MenuBar;
import com.github.chaosmelone9.datavisualizer.ui.components.optionpane.OptionPane;

import javax.swing.*;

public class WindowContent extends JRootPane {

    MainWindow window;
    OptionPane optionPane;
    ContentPane contentPane;
    JSplitPane splitPane;
    int dividerLocation = 200;

    public WindowContent(MainWindow instance) {
        this.window = instance;
        setJMenuBar(new MenuBar(window));
        this.optionPane = new OptionPane();
        this.contentPane = new ContentPane();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionPane, contentPane);
        splitPane.setDividerLocation(dividerLocation);
        setContentPane(splitPane);
    }

    public void toggleOptionPane(boolean enabled) {
        if(!enabled) {
            dividerLocation = splitPane.getDividerLocation();
        } else {
            splitPane.setDividerLocation(dividerLocation);
        }
        splitPane.setEnabled(enabled);
        optionPane.setVisible(enabled);
    }
}
