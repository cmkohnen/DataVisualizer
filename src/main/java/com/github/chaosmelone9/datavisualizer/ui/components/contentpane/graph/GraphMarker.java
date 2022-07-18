package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphMarker extends GraphObject {
    boolean xOrY;
    double value;

    public GraphMarker(boolean xOrY, double value, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, colour);
        this.xOrY = xOrY;
        this.value = value;
    }
}
