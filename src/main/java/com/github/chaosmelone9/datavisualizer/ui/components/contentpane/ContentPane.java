package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class ContentPane extends JTabbedPane{
    private final Graph graph = new Graph();
    private final Table table = new Table();
    private final GraphComponents graphComponents = new GraphComponents();
    public ContentPane(MainWindow window) {
        add(graph, "Graph");
        add(table, "Table");
        add(graphComponents, "Functions and other markings");
    }

    public Graph getGraph() {
        return graph;
    }

    public Table getTable() {
        return table;
    }

    public GraphComponents getGraphComponents() {
        return graphComponents;
    }
}
