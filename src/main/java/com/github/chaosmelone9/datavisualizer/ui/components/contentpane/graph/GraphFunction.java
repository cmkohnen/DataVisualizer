package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;

public class GraphFunction {
    public boolean allocateToRightAxis = true;

    public Color colour = new Color(1,1,1);

    public double function(double x) {
        return 1.5 * x + 1;
    }
}
