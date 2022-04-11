package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphMarker {
    boolean xOrY;
    double value;
    boolean allocateToRightAxis;
    Color colour;

    public GraphMarker(boolean xOrY, double value, boolean allocateToRightAxis, Color colour) {
        this.xOrY = xOrY;
        this.value = value;
        this.allocateToRightAxis = allocateToRightAxis;
        this.colour = colour;
    }
}
