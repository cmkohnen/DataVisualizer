package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.GraphFunction;

import javax.swing.*;

public class ContentPane extends JTabbedPane{
    private final Graph graph;
    public ContentPane() {
        this.graph = new Graph();
        Table table = new Table();
        add(graph, "Graph");
        add(new JScrollPane(table), "Table");

        //graph.setBackgroundColour(new Color(158, 19, 192));
        graph.setTitle("Test title");
        graph.addFunction(new GraphFunction());
    }

    public Graph getGraph() {
        return graph;
    }
}
