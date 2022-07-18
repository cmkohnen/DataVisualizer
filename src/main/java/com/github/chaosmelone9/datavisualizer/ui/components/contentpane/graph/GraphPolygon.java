package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphPolygon extends GraphObject {
    com.github.chaosmelone9.datavisualizer.datasets.Polygon polygon;
    boolean filled;

    public GraphPolygon(com.github.chaosmelone9.datavisualizer.datasets.Polygon polygon, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour, boolean filled) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, colour);
        this.polygon = polygon;
        this.filled = filled;
    }
}
