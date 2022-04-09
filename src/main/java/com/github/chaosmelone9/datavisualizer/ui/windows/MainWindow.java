package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.ContentPane;
import com.github.chaosmelone9.datavisualizer.ui.components.menubar.MenuBar;
import com.github.chaosmelone9.datavisualizer.ui.components.optionpane.OptionPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {

    private final Main instance;
    private final OptionPane optionPane;
    private final ContentPane contentPane;
    private final JSplitPane splitPane;
    public int dividerLocation = 200;

    public MainWindow(Main instance) throws IOException {
        super();
        this.instance = instance;
        this.optionPane = new OptionPane();
        this.contentPane = new ContentPane(this);
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionPane, contentPane);
        splitPane.setDividerLocation(dividerLocation);

        setTitle("DataVisualizer");
        setLocationRelativeTo(null);
        setSize(1000,800);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(ImageIO.read(instance.getFetcher().fetch("icon.png")));
        setContentPane(splitPane);
        setJMenuBar(new MenuBar(this));
        setVisible(true);
    }

    public Main getInstance() {
        return instance;
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
