package com.github.chaosmelone9.datavisualizer.ui.windows;

import javax.swing.*;
import java.awt.*;

public class GraphCustomizerWindow extends PopupWindow{
    public GraphCustomizerWindow(MainWindow window) {
        super("Customize Graph", window);
        constraints.gridx = 0;
        for(int i = 0; i < 100; i++) {
            constraints.gridy = i;
            add(new JButton("Click me"));
        }
        content.setBackground(new Color(19, 62, 162));
    }
}
