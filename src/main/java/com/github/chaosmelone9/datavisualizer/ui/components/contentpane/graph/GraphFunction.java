package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import java.awt.*;
import java.util.function.Function;

public class GraphFunction extends GraphObject {
    public Function<Double, Double> function;

    public GraphFunction(Function<Double, Double> function, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, colour);
        this.function = function;
    }
}
