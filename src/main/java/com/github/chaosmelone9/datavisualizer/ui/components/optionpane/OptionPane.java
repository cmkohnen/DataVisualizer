package com.github.chaosmelone9.datavisualizer.ui.components.optionpane;

import javax.swing.*;

public class OptionPane extends JTabbedPane {
    public OptionPane() {
        super();
        add(new JScrollPane(new Options()), "Options");
    }
}
