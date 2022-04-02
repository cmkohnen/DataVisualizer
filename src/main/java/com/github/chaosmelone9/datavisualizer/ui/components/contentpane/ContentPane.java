package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.Graph.Graph;

import javax.swing.*;

public class ContentPane extends JTabbedPane{
    public ContentPane() {
        add(new Graph(), "Graph");
        add(new JScrollPane(new Table()), "Table");
    }
}
