package com.github.chaosmelone9.datavisualizer.datasets;

public class DataObject {
    protected Type type;

    public DataObject() {
        this.type = null;
    }

    public Type getType() {
        return type;
    }

    public static enum Type {
        ROW, POLYGON, POINT, OVAL
    }
}
