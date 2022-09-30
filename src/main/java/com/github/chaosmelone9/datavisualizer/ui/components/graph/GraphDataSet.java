// SPDX-License-Identifier: GPL-3.0-or-later
/*
 *  DataVisualizer
 *  Copyright (C) 2022 Christoph Kohnen <christoph.kohnen@gymbane.eu>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.chaosmelone9.datavisualizer.ui.components.graph;

import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import java.util.ArrayList;
import java.util.List;

public class GraphDataSet {
    private final List<GraphFunction> graphFunctions;
    private final List<GraphMarker> graphMarkers;
    private final List<GraphOval> graphOvals;
    private final List<GraphPoint> graphPoints;
    private final List<GraphPolygon> graphPolygons;
    private final List<GraphRow> graphRows;
    private final Graph graph;
    public GraphDataSet(MainWindow window) {
        this.graph = window.getContentPane().getGraph();
        this.graphFunctions = new ArrayList<>();
        this.graphMarkers = new ArrayList<>();
        this.graphOvals = new ArrayList<>();
        this.graphPolygons = new ArrayList<>();
        this.graphPoints = new ArrayList<>();
        this.graphRows = new ArrayList<>();
    }

    public void addFunction(GraphFunction graphFunction) {
        this.graphFunctions.add(graphFunction);
        this.graph.addFunction(graphFunction);
    }

    public void addGraphMarker(GraphMarker graphMarker) {
        this.graphMarkers.add(graphMarker);
        this.graph.addMarker(graphMarker);
    }

    public void addGraphOval(GraphOval graphOval) {
        this.graphOvals.add(graphOval);
        this.graph.addOval(graphOval);
    }

    public void addGraphPolygon(GraphPolygon graphPolygon) {
        this.graphPolygons.add(graphPolygon);
        this.graph.addPolygon(graphPolygon);
    }

    public void addGraphPoint(GraphPoint graphPoint) {
        this.graphPoints.add(graphPoint);
        this.graph.addPoint(graphPoint);
    }

    public void addRow(GraphRow row) {
        this.graphRows.add(row);
        this.graph.addRow(row);
    }

    public List<GraphFunction> getGraphFunctions() {
        return graphFunctions;
    }

    public List<GraphMarker> getGraphMarkers() {
        return graphMarkers;
    }

    public List<GraphOval> getGraphOvals() {
        return graphOvals;
    }

    public List<GraphPolygon> getGraphPolygons() {
        return graphPolygons;
    }

    public List<GraphPoint> getGraphPoints() {
        return graphPoints;
    }

    public List<GraphRow> getGraphRows() {
        return graphRows;
    }
}
