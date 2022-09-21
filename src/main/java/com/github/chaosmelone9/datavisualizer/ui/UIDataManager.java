package com.github.chaosmelone9.datavisualizer.ui;

import com.github.chaosmelone9.datavisualizer.datasets.DataSet;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.GraphDataSet;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

public class UIDataManager {
    private final MainWindow window;
    private final DataSet dataSet;
    private final GraphDataSet graphDataSet;

    public UIDataManager(MainWindow window) {
        this.window = window;
        this.dataSet = new DataSet();
        this.graphDataSet = new GraphDataSet(window);
    }
}
