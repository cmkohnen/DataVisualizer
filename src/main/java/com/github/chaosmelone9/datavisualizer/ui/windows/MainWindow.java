package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.ContentPane;
import com.github.chaosmelone9.datavisualizer.ui.components.menubar.MenuBar;
import com.github.chaosmelone9.datavisualizer.ui.components.optionpane.OptionPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {

    private final Content content;
    private final Main instance;

    public MainWindow(Main instance) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        super();
        this.instance = instance;
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setTitle("DataVisualizer");
        setLocationRelativeTo(null);
        setSize(1000,800);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.content = new Content(this);
        setRootPane(content);
        setIconImage(ImageIO.read(instance.getFetcher().fetch("icon.png")));
        setVisible(true);
    }

    public Main getInstance() {
        return instance;
    }

    public void toggleOptionPane(boolean enabled) {
        if(!enabled) {
            content.dividerLocation = content.splitPane.getDividerLocation();
        } else {
            content.splitPane.setDividerLocation(content.dividerLocation);
        }
        content.splitPane.setEnabled(enabled);
        content.optionPane.setVisible(enabled);
    }

    public OptionPane getOptionPane() {
        return content.optionPane;
    }

    public ContentPane getContentPane() {
        return content.contentPane;
    }

    private static class Content extends JRootPane {

        public final OptionPane optionPane;
        public final ContentPane contentPane;
        public final JSplitPane splitPane;
        public int dividerLocation = 200;

        public Content(MainWindow instance) throws IOException {
            setJMenuBar(new MenuBar(instance));
            this.optionPane = new OptionPane();
            this.contentPane = new ContentPane(instance);
            this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionPane, contentPane);
            splitPane.setDividerLocation(dividerLocation);
            setContentPane(splitPane);
        }
    }
}
