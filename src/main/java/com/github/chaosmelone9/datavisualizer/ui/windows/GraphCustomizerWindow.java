package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GraphCustomizerWindow extends PopupWindow{
    Graph graph;
    public GraphCustomizerWindow(MainWindow window) {
        super("Customize Graph", window);
        this.graph = window.getContentPane().getGraph();

        //Checkboxes
        add(new JLabel("Select Components"));
        add(new CheckBox("Draw x grid", graph.isDrawXGrid(), true, actionEvent -> graph.setDrawXGrid(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw y grid", graph.isDrawYGrid(), true, actionEvent -> graph.setDrawYGrid(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw x hatch marks", graph.isDrawXHatchMarks(), true, actionEvent -> graph.setDrawXHatchMarks(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw hatch marks of left y-axis", graph.isDrawYAHatchMarks(), true, actionEvent -> graph.setDrawYAHatchMarks(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw hatch marks of right y-axis", graph.isDrawYBHatchMarks(), graph.hasSecondYAxis(), actionEvent -> graph.setDrawYBHatchMarks(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw x labels", graph.isDrawXLabels(), true, actionEvent -> graph.setDrawXLabels(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw y labels of left y-axis", graph.isDrawYALabels(), true, actionEvent -> graph.setDrawYALabels(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw y labels of right y-axis", graph.isDrawYBLabels(), graph.hasSecondYAxis(), actionEvent -> graph.setDrawYBLabels(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Indicate x position of mouse-pointer", graph.isIndicateMouseX(), true, actionEvent -> graph.setIndicateMouseX(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Indicate y position of mouse-pointer", graph.isIndicateMouseY(), true, actionEvent -> graph.setIndicateMouseY(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Label x and y position of mouse-pointer", graph.isLabelMouseXY(), true, actionEvent -> graph.setLabelMouseXY(((CheckBox) actionEvent.getSource()).isSelected())));
    }

    private static class CheckBox extends JCheckBox {
        public CheckBox(String name, boolean selected, boolean enabled, ActionListener actionListener) {
            super(name);
            setSelected(selected);
            setEnabled(enabled);
            addActionListener(actionListener);
        }
    }
}
