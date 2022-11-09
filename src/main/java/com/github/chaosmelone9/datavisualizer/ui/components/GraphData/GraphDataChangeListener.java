package com.github.chaosmelone9.datavisualizer.ui.components.GraphData;

import com.github.chaosmelone9.datavisualizer.ui.components.graph.GraphObject;

public interface GraphDataChangeListener {
    void onGraphDataChange(ChangeType type, GraphObject object);

    enum ChangeType {
        ADD, REMOVE
    }
}

