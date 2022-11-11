package com.github.chaosmelone9.datavisualizer.ui.components.optionpane;

import com.github.chaosmelone9.datavisualizer.ui.components.graph.Graph;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.GraphObject;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GraphObjectCustomizer extends JPanel {
    MainWindow window;
    GraphObject graphObject;
    JTextField name;
    JLabel type;
    JButton colourChooser;
    JRadioButton allocateToSecondXAxis;
    JRadioButton allocateToSecondYAxis;
    JRadioButton visible;
    public GraphObjectCustomizer(MainWindow window, @Nullable GraphObject object) {
        super();
        this.window = window;
        this.graphObject = object;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.name = new JTextField();
        name.setEditable(true);
        name.addActionListener(actionEvent -> graphObject.name = name.getText());
        this.type = new JLabel();
        add(name);
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

        if(object != null) {
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
    }

    private void dataUpdated() {
        Graph graph = window.getContentPane().getGraph();
        graph.updateSecondAxes();
        graph.repaint();
    }
}
