package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphFunction {
    public boolean allocateToRightAxis = false;

    public Color colour = new Color(1,1,1);

    public double function(double x) {
        return x * x * x;
    }
}
