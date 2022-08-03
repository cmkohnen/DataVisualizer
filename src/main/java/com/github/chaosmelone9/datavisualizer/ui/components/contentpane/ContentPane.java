package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;
import com.github.chaosmelone9.datavisualizer.ui.windows.GraphPopupWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class ContentPane extends JTabbedPane{
    private final Graph graph;
    private final Table table = new Table();
    private final GraphComponents graphComponents = new GraphComponents();

    private final MainWindow window;

    private GraphPopupWindow graphPopupWindow;

    private boolean graphDetached = false;
    public ContentPane(MainWindow window) {
        this.window = window;
        this.graph = new Graph(window.getInstance());
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

    public void manageGraph() {
        if(!graphDetached) {
            detachGraph();
        } else {
            reattachGraph();
            graphPopupWindow.dispose();
        }
    }

    private void detachGraph() {
        graphPopupWindow = new GraphPopupWindow(window, graph);
        graphDetached = true;
    }

    public void reattachGraph() {
        removeAll();
        add(graph, "Graph");
        add(table, "Table");
        add(graphComponents, "Functions and other markings");
        graphDetached = false;
    }

    public boolean isGraphDetached() {
        return graphDetached;
    }
}
