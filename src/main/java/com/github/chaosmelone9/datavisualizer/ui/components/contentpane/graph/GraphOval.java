package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import com.github.chaosmelone9.datavisualizer.datasets.Oval;

import java.awt.*;

public class GraphOval extends GraphObject {
    Oval oval;
    boolean filled;

    public GraphOval(Oval oval, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour, boolean filled) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, colour);
        this.oval = oval;
        this.filled = filled;
    }
}
