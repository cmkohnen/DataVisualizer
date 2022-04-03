package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import com.github.chaosmelone9.datavisualizer.ui.MainWindow;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class ContentPane extends JTabbedPane{
    public ContentPane(MainWindow instance) {
        Graph graph = new Graph(instance.getInstance().getConfig().getGraphConfig());
        Table table = new Table();
        add(graph, "Graph");
        add(new JScrollPane(table), "Table");

        graph.getConfig().backgroundColour = new Color(158, 19, 192);
    }
}
