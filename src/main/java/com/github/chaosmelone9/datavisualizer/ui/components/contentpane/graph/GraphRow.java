package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphRow {
    com.github.chaosmelone9.datavisualizer.datasets.Row row;
    boolean allocateToRightAxis;
    Color colour;

    public GraphRow(com.github.chaosmelone9.datavisualizer.datasets.Row row, boolean allocateToRightAxis, Color colour) {
        this.row = row;
        this.allocateToRightAxis = allocateToRightAxis;
        this.colour = colour;
    }
}
