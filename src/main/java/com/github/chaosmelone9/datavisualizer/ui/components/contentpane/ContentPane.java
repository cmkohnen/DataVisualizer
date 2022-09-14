// SPDX-License-Identifier: GPL-3.0-or-later
/*
 *  DataVisualizer
 *  Copyright (C) 2022 Christoph Kohnen <christoph.kohnen@gymbane.eu>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.chaosmelone9.datavisualizer.ui.components.contentpane;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.GraphFunction;
import com.github.chaosmelone9.datavisualizer.ui.windows.GraphPopupWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

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
        Function<Double, Double> function = y -> y * y;
        graph.addFunction(new GraphFunction(function,true,true, Color.BLACK));
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
