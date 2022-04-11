package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import com.github.chaosmelone9.datavisualizer.datasets.Oval;

import java.awt.*;

public class GraphOval {
    Oval oval;
    boolean allocateToRightAxis;
    Color colour;
    boolean filled;

    public GraphOval(Oval oval, boolean allocateToRightAxis, Color colour, boolean filled) {
        this.oval = oval;
        this.allocateToRightAxis = allocateToRightAxis;
        this.colour = colour;
        this.filled = filled;
    }
}
