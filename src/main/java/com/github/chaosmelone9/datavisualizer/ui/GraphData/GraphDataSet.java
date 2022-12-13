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
package com.github.chaosmelone9.datavisualizer.ui.GraphData;

import com.github.chaosmelone9.datavisualizer.config.GraphConfig;
import com.github.chaosmelone9.datavisualizer.datasets.*;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.*;

import java.util.ArrayList;
import java.util.List;

public class GraphDataSet {
    private final List<GraphDataChangeListener> graphDataChangeListeners = new ArrayList<>();
    private final DataSet dataSet;

    public GraphDataSet() {
        this.dataSet = new DataSet();
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void addListener(GraphDataChangeListener listener) {
        graphDataChangeListeners.add(listener);
    }

    public void add(GraphObject object) {
        dataSet.graphObjects.add(object);
        switch (object.getType()) {
            case GRAPHFUNCTION -> dataSet.graphFunctions.add((GraphFunction) object);
            case GRAPHMARKER -> dataSet.graphMarkers.add((GraphMarker) object);
            case GRAPHOVAL -> dataSet.graphOvals.add((GraphOval) object);
            case GRAPHPOINT -> dataSet.graphPoints.add((GraphPoint) object);
            case GRAPHPOLYGON -> dataSet.graphPolygons.add((GraphPolygon) object);
            case GRAPHROW -> dataSet.graphRows.add((GraphRow) object);
        }
        for (GraphDataChangeListener listener : graphDataChangeListeners) {
            listener.onGraphDataChange(GraphDataChangeListener.ChangeType.ADD, object);
        }
    }

    public void addAll(List<GraphObject> objects) {
        for (GraphObject object : objects) {
            add(object);
        }
    }

    public void addAll(DataSet dataSet) {
        for (GraphObject object : dataSet.graphObjects) {
            add(object);
        }
    }

    public void add(DataObject dataObject) {
        switch (dataObject.getType()) {
            case OVAL ->
                    dataSet.graphOvals.add(new GraphOval((Oval) dataObject, String.format("Oval %d", dataSet.graphOvals.size()), false, false, GraphConfig.DEFAULT_OVAL_COLOUR, true, true));
            case POLYGON ->
                    dataSet.graphPolygons.add(new GraphPolygon((Polygon) dataObject, String.format("Polygon %d", dataSet.graphPolygons.size()), false, false, GraphConfig.DEFAULT_POLYGON_COLOUR, true, true));
            case POINT ->
                    dataSet.graphPoints.add(new GraphPoint((Point) dataObject, String.format("Point %d", dataSet.graphPoints.size()), false, false, GraphConfig.DEFAULT_POINT_COLOUR, true));
            case ROW ->
                    dataSet.graphRows.add(new GraphRow((Row) dataObject, String.format("Row %d", dataSet.graphRows.size()), false, false, GraphConfig.DEFAULT_ROW_COLOUR, true));
        }
    }

    public void remove(GraphObject object) {
        dataSet.graphObjects.remove(object);
        switch (object.getType()) {
            case GRAPHFUNCTION -> dataSet.graphFunctions.remove((GraphFunction) object);
            case GRAPHMARKER -> dataSet.graphMarkers.remove((GraphMarker) object);
            case GRAPHOVAL -> dataSet.graphOvals.remove((GraphOval) object);
            case GRAPHPOINT -> dataSet.graphPoints.remove((GraphPoint) object);
            case GRAPHPOLYGON -> dataSet.graphPolygons.remove((GraphPolygon) object);
            case GRAPHROW -> dataSet.graphRows.remove((GraphRow) object);
        }
        for (GraphDataChangeListener listener : graphDataChangeListeners) {
            listener.onGraphDataChange(GraphDataChangeListener.ChangeType.REMOVE, object);
        }
    }

    public void dataChanged() {
        for (GraphDataChangeListener listener : graphDataChangeListeners) {
            listener.onGraphDataChange(GraphDataChangeListener.ChangeType.UPDATE, null);
        }
    }

    public List<GraphFunction> getGraphFunctions() {
        return dataSet.graphFunctions;
    }

    public List<GraphMarker> getGraphMarkers() {
        return dataSet.graphMarkers;
    }

    public List<GraphOval> getGraphOvals() {
        return dataSet.graphOvals;
    }

    public List<GraphPolygon> getGraphPolygons() {
        return dataSet.graphPolygons;
    }

    public List<GraphPoint> getGraphPoints() {
        return dataSet.graphPoints;
    }

    public List<GraphRow> getGraphRows() {
        return dataSet.graphRows;
    }

    public List<GraphObject> getGraphObjects() {
        return dataSet.graphObjects;
    }
}
