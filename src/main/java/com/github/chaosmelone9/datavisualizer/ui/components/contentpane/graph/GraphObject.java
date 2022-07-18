package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphObject {
    Color colour;
    boolean allocateToSecondYAxis;
    boolean allocateToSecondXAxis;

    public GraphObject(boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour) {
        this.colour = colour;
        this.allocateToSecondYAxis = allocateToSecondYAxis;
        this.allocateToSecondXAxis = allocateToSecondXAxis;
    }
}
