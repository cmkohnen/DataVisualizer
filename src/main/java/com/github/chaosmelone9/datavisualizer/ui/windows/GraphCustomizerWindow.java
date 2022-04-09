package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;

import javax.swing.*;

public class GraphCustomizerWindow extends PopupWindow{
    Graph graph;
    public GraphCustomizerWindow(MainWindow window) {
        super("Customize Graph", window);
        this.graph = window.getContentPane().getGraph();

        //Checkboxes
        add(new JLabel("Select Components"));

        //TODO this could probably be optimized but I'm too dumb to figure it out
        JCheckBox drawXGrid = new JCheckBox("Draw x grid");
        drawXGrid.setSelected(graph.isDrawXGrid());
        drawXGrid.addActionListener(actionEvent -> graph.setDrawXGrid(drawXGrid.isSelected()));
        add(drawXGrid);

        JCheckBox drawYGrid = new JCheckBox("Draw y grid");
        drawYGrid.setSelected(graph.isDrawYGrid());
        drawYGrid.addActionListener(actionEvent -> graph.setDrawYGrid(drawYGrid.isSelected()));
        add(drawYGrid);

        JCheckBox drawXHatchMarks = new JCheckBox("Draw x hatch marks");
        drawXHatchMarks.setSelected(graph.isDrawXHatchMarks());
        drawXHatchMarks.addActionListener(actionEvent -> graph.setDrawXHatchMarks(drawXHatchMarks.isSelected()));
        add(drawXHatchMarks);

        JCheckBox drawYAHatchMarks = new JCheckBox("Draw hatch marks of left y-axis");
        drawYAHatchMarks.setSelected(graph.isDrawYAHatchMarks());
        drawYAHatchMarks.addActionListener(actionEvent -> graph.setDrawYAHatchMarks(drawYAHatchMarks.isSelected()));
        add(drawYAHatchMarks);

        JCheckBox drawYBHatchMarks = new JCheckBox("Draw hatch marks of right y-axis");
        drawYBHatchMarks.setSelected(graph.isDrawYBHatchMarks());
        drawYBHatchMarks.setEnabled(graph.hasSecondYAxis());
        drawYBHatchMarks.addActionListener(actionEvent -> graph.setDrawYBHatchMarks(drawYBHatchMarks.isSelected()));
        add(drawYBHatchMarks);

        JCheckBox drawXLabels = new JCheckBox("Draw x labels");
        drawXLabels.setSelected(graph.isDrawXLabels());
        drawXLabels.addActionListener(actionEvent -> graph.setDrawXLabels(drawXLabels.isSelected()));
        add(drawXLabels);

        JCheckBox drawYALabels = new JCheckBox("Draw y labels of left y-axis");
        drawYALabels.setSelected(graph.isDrawYALabels());
        drawYALabels.addActionListener(actionEvent -> graph.setDrawYALabels(drawYALabels.isSelected()));
        add(drawYALabels);

        JCheckBox drawYBLabels = new JCheckBox("Draw y labels of right y-axis");
        drawYBLabels.setSelected(graph.isDrawYBLabels());
        drawYBLabels.setEnabled(graph.hasSecondYAxis());
        drawYBLabels.addActionListener(actionEvent -> graph.setDrawYBLabels(drawYBLabels.isSelected()));
        add(drawYBLabels);

        JCheckBox indicateMouseX = new JCheckBox("Indicate x position of mouse-pointer");
        indicateMouseX.setSelected(graph.isIndicateMouseX());
        indicateMouseX.addActionListener(actionEvent -> graph.setIndicateMouseX(indicateMouseX.isSelected()));
        add(indicateMouseX);

        JCheckBox indicateMouseY = new JCheckBox("Indicate y position of mouse-pointer");
        indicateMouseY.setSelected(graph.isIndicateMouseY());
        indicateMouseY.addActionListener(actionEvent -> graph.setIndicateMouseY(indicateMouseY.isSelected()));
        add(indicateMouseY);

        JCheckBox labelMouseXY = new JCheckBox("Label x and y position of mouse-pointer");
        labelMouseXY.setSelected(graph.isLabelMouseXY());
        labelMouseXY.addActionListener(actionEvent -> graph.setLabelMouseXY(labelMouseXY.isSelected()));
        add(labelMouseXY);
    }
}
