package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.GraphFunction;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ContentPane extends JTabbedPane{
    private final Graph graph;
    public ContentPane(MainWindow window) throws IOException {
        this.graph = new Graph();
        Table table = new Table();
        add(graph, "Graph");
        add(new JScrollPane(table), "Table");

        //graph.setBackgroundColour(new Color(158, 19, 192));
        graph.setTitle("Test title");
        graph.addFunction(new GraphFunction());
        BufferedImage image = ImageIO.read(window.getInstance().getFetcher().fetch("icon.png"));
        graph.setBackgroundImage(image);
    }

    public Graph getGraph() {
        return graph;
    }
}
