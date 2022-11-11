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
package com.github.chaosmelone9.datavisualizer.ui.components.GraphData;

import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.*;

import java.util.ArrayList;
import java.util.List;

public class GraphDataSet {

    private final List<GraphDataChangeListener> graphDataChangeListeners = new ArrayList<>();
    private final List<GraphObject> graphObjects;
    private final List<GraphFunction> graphFunctions;
    private final List<GraphMarker> graphMarkers;
    private final List<GraphOval> graphOvals;
    private final List<GraphPoint> graphPoints;
    private final List<GraphPolygon> graphPolygons;
    private final List<GraphRow> graphRows;

    public GraphDataSet() {
        this.graphObjects = new ArrayList<>();
        this.graphFunctions = new ArrayList<>();
        this.graphMarkers = new ArrayList<>();
        this.graphOvals = new ArrayList<>();
        this.graphPolygons = new ArrayList<>();
        this.graphPoints = new ArrayList<>();
        this.graphRows = new ArrayList<>();
    }

    public void addListener(GraphDataChangeListener listener) {
        graphDataChangeListeners.add(listener);
    }

    public void add(GraphObject object) {
        graphObjects.add(object);
        switch (object.getType()) {
            case GRAPHFUNCTION -> graphFunctions.add((GraphFunction) object);
            case GRAPHMARKER -> graphMarkers.add((GraphMarker) object);
            case GRAPHOVAL -> graphOvals.add((GraphOval) object);
            case GRAPHPOINT -> graphPoints.add((GraphPoint) object);
            case GRAPHPOLYGON -> graphPolygons.add((GraphPolygon) object);
            case GRAPHROW -> graphRows.add((GraphRow) object);
        }
        for (GraphDataChangeListener listener : graphDataChangeListeners) {
            listener.onGraphDataChange(GraphDataChangeListener.ChangeType.ADD, object);
        }
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

    public List<GraphObject> getGraphObjects() {
        return graphObjects;
    }
}
