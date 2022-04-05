package com.github.chaosmelone9.datavisualizer.ui.components;

import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.ContentPane;
import com.github.chaosmelone9.datavisualizer.ui.components.menubar.MenuBar;
import com.github.chaosmelone9.datavisualizer.ui.components.optionpane.OptionPane;

import javax.swing.*;

public class WindowContent extends JRootPane {

    private final OptionPane optionPane;
    private final ContentPane contentPane;
    private final JSplitPane splitPane;
    private int dividerLocation = 200;

    public WindowContent(MainWindow instance) {
        setJMenuBar(new MenuBar(instance));
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

    public OptionPane getOptionPane() {
        return optionPane;
    }

    public ContentPane getContentPane() {
        return contentPane;
    }
}
