package com.github.chaosmelone9.datavisualizer.ui.components.optionpane;

import com.github.chaosmelone9.datavisualizer.ui.components.graph.Graph;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.*;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GraphObjectCustomizer extends JPanel {
    MainWindow window;
    GraphObject graphObject;
    JTextField name;
    JLabel type;
    JButton colourChooser;
    JRadioButton allocateToSecondXAxis;
    JRadioButton allocateToSecondYAxis;
    JRadioButton visible;
    JRadioButton filled;
    ActionListener filledListener;

    public GraphObjectCustomizer(MainWindow window, @Nullable GraphObject object) {
        super();
        this.window = window;
        this.graphObject = object;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Name"));
        this.name = new JTextField();
        name.setEditable(true);
        name.addActionListener(actionEvent -> graphObject.name = name.getText());
        name.setMaximumSize(new Dimension(200, 30));
        this.type = new JLabel();
        add(name);
        add(new JLabel("Type"));
        add(type);

        this.colourChooser = new JButton("Choose colour");
        colourChooser.addActionListener(actionEvent -> {
            graphObject.colour = JColorChooser.showDialog(window, "Choose colour", graphObject.colour);
            dataUpdated();
        });
        add(colourChooser);

        this.allocateToSecondXAxis = new JRadioButton("second X axis");
        allocateToSecondXAxis.addActionListener(actionEvent -> {
            graphObject.allocateToSecondXAxis = allocateToSecondXAxis.isSelected();
            dataUpdated();
        });
        add(allocateToSecondXAxis);

        this.allocateToSecondYAxis = new JRadioButton("second Y axis");
        allocateToSecondYAxis.addActionListener(actionEvent -> {
            graphObject.allocateToSecondYAxis = allocateToSecondYAxis.isSelected();
            dataUpdated();
        });
        add(allocateToSecondYAxis);

        this.visible = new JRadioButton("visible");
        visible.addActionListener(actionEvent -> {
            graphObject.visible = visible.isSelected();
            dataUpdated();
        });
        add(visible);

        this.filled = new JRadioButton("filled");
        filled.setEnabled(false);
        add(filled);

        if (object != null) {
            setObject(object);
        }
    }

    public void setObject(@NotNull GraphObject object) {
        this.graphObject = object;
        name.setText(object.name);
        type.setText(object.type.toString());
        allocateToSecondXAxis.setSelected(object.allocateToSecondXAxis);
        allocateToSecondYAxis.setSelected(object.allocateToSecondYAxis);
        visible.setSelected(object.visible);
        switch (object.type) {
            case GRAPHOVAL -> {
                filled.setEnabled(true);
                filled.setSelected(((GraphOval) object).filled);
                filled.removeActionListener(filledListener);
                this.filledListener = actionEvent -> {
                    ((GraphOval) object).filled = filled.isSelected();
                    dataUpdated();
                };
                filled.addActionListener(filledListener);
            }
            case GRAPHPOLYGON -> {
                filled.setEnabled(true);
                filled.setSelected(((GraphPolygon) object).filled);
                filled.removeActionListener(filledListener);
                this.filledListener = actionEvent -> {
                    ((GraphPolygon) object).filled = filled.isSelected();
                    dataUpdated();
                };
                filled.addActionListener(filledListener);
            }
            case GRAPHROW, GRAPHFUNCTION, GRAPHMARKER, GRAPHPOINT -> {
                filled.setEnabled(false);
                filled.setSelected(false);
            }
        }
    }

    private void dataUpdated() {
        Graph graph = window.getContentPane().getGraph();
        graph.updateSecondAxes();
        graph.repaint();
    }
}
