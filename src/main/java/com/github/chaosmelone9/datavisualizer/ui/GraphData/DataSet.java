package com.github.chaosmelone9.datavisualizer.ui.GraphData;

import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.*;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    protected final List<GraphObject> graphObjects;
    protected final List<GraphFunction> graphFunctions;
    protected final List<GraphMarker> graphMarkers;
    protected final List<GraphOval> graphOvals;
    protected final List<GraphPoint> graphPoints;
    protected final List<GraphPolygon> graphPolygons;
    protected final List<GraphRow> graphRows;

    public DataSet() {
        this.graphObjects = new ArrayList<>();
        this.graphFunctions = new ArrayList<>();
        this.graphMarkers = new ArrayList<>();
        this.graphOvals = new ArrayList<>();
        this.graphPolygons = new ArrayList<>();
        this.graphPoints = new ArrayList<>();
        this.graphRows = new ArrayList<>();
    }
}
