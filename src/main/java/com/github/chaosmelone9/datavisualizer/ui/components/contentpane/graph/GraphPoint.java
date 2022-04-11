package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphPoint {
    com.github.chaosmelone9.datavisualizer.datasets.Point point;
    boolean allocateToRightAxis;
    Color color;

    public GraphPoint(com.github.chaosmelone9.datavisualizer.datasets.Point point , boolean allocateToRightAxis, Color color) {
        this.point = point;
        this.allocateToRightAxis = allocateToRightAxis;
        this.color = color;
    }
}
