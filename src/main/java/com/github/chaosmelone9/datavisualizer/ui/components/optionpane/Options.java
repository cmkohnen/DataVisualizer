package com.github.chaosmelone9.datavisualizer.ui.components.optionpane;

import javax.swing.*;
import java.awt.*;

public class Options extends JPanel {
    public Options() {
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        add(new JLabel("Example Text"), constraints);
        setBackground(new Color(33, 62, 176));
        for(int i = 0; i < 100; i++) {
            constraints.gridy = i + 1;
            add(new JButton("Click me"), constraints);
        }
    }
}
