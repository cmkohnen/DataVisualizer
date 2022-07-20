package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.windows.GraphCustomizerWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class ViewMenu extends Menu {
    private final CheckBoxMenuItem detachGraph;
    public ViewMenu(MainWindow window) {
        super(window, "View");

        this.detachGraph = new CheckBoxMenuItem("Detach Graph", false, actionEvent -> window.getContentPane().manageGraph());

        add(new CheckBoxMenuItem("Show options menu", true, actionEvent -> window.toggleOptionPane(((JCheckBoxMenuItem) (actionEvent.getSource())).isSelected())));
        add(new MenuItem("Customize Graph...", actionEvent -> new GraphCustomizerWindow(window)));
        add(detachGraph);
    }

    public void reattachGraph() {
        detachGraph.setSelected(false);
        window.getContentPane().reattachGraph();
    }
}
