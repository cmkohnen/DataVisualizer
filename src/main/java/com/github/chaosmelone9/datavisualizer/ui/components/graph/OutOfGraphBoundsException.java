package com.github.chaosmelone9.datavisualizer.ui.components.graph;

class OutOfGraphBoundsException extends Exception {
    public OutOfGraphBoundsException(String value) {
        super(String.format("%s is out of graph bounds", value));
    }
}
