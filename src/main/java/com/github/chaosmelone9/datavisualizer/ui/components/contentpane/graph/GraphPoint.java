package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphPoint extends GraphObject {
    com.github.chaosmelone9.datavisualizer.datasets.Point point;

    public GraphPoint(com.github.chaosmelone9.datavisualizer.datasets.Point point , boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color color) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, color);
        this.point = point;
    }
}
