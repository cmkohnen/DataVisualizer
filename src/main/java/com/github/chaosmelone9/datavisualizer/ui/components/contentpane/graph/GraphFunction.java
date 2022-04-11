package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;
import java.util.function.Function;

public class GraphFunction {
    public Function<Double, Double> function;
    public Color colour;
    public boolean allocateToRightAxis;

    public GraphFunction(Function<Double, Double> function, boolean allocateToRightAxis, Color colour) {
        this.function = function;
        this.colour = colour;
        this.allocateToRightAxis = allocateToRightAxis;
    }
}
