package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphRow extends GraphObject {
    com.github.chaosmelone9.datavisualizer.datasets.Row row;

    public GraphRow(com.github.chaosmelone9.datavisualizer.datasets.Row row, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, colour);
        this.row = row;
    }
}
