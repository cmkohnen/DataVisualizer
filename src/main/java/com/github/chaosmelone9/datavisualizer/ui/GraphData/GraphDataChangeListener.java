package com.github.chaosmelone9.datavisualizer.ui.GraphData;

import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.GraphObject;

public interface GraphDataChangeListener {
    void onGraphDataChange(ChangeType type, GraphObject object);

    enum ChangeType {
        ADD,
        REMOVE,
        UPDATE
    }
}
