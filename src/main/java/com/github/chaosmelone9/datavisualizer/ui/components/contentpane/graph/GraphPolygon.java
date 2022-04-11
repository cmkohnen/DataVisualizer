package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphPolygon {
    com.github.chaosmelone9.datavisualizer.datasets.Polygon polygon;
    boolean allocateToRightAxis;
    Color colour;
    boolean filled;

    public GraphPolygon(com.github.chaosmelone9.datavisualizer.datasets.Polygon polygon, boolean allocateToRightAxis, Color colour, boolean filled) {
        this.polygon = polygon;
        this.allocateToRightAxis = allocateToRightAxis;
        this.colour = colour;
        this.filled = filled;
    }
}
