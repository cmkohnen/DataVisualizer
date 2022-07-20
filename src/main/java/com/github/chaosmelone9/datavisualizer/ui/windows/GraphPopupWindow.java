package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphPopupWindow extends PopupWindow {
    public GraphPopupWindow(MainWindow window, Graph graph) {
        super("Graph", window);
        setContentPane(graph);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                e.getWindow().dispose();
                window.getJMenuBar().getViewMenu().reattachGraph();
            }
        });
    }
}
