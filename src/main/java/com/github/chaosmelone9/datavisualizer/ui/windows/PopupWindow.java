package com.github.chaosmelone9.datavisualizer.ui.windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PopupWindow extends JFrame {
    protected MainWindow mainWindow;
    protected final JPanel content;
    protected final GridBagLayout layout;
    protected final GridBagConstraints constraints;

    public PopupWindow(String title, MainWindow window) {
        this.mainWindow = window;
        this.content = new JPanel();
        this.layout = new GridBagLayout();
        this.constraints = layout.getConstraints(content);
        JScrollPane pane = new JScrollPane(content);
        setContentPane(pane);
        content.setLayout(layout);
        setTitle(title);
        setLocationRelativeTo(window);
        setSize((int) (window.getWidth() - window.getWidth() * .25), (int) (window.getHeight() - window.getHeight() * .25));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        try {
            setIconImage(ImageIO.read(window.getInstance().getFetcher().fetch("icon.png")));
        } catch (IOException e) {
            mainWindow.getInstance().getLogger().logStackTrace(e);
        }
        setVisible(true);
    }

    protected void add(JComponent component) {
        constraints.gridy = constraints.gridy + 1;
        content.add(component, constraints);
    }
}
