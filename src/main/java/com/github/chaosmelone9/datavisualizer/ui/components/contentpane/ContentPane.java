package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import javax.swing.*;
import java.awt.*;

public class ContentPane extends JTabbedPane{
    public ContentPane() {
        add(new Graph(), "Graph");
        add(new JScrollPane(new Table()), "Table");
    }
}
