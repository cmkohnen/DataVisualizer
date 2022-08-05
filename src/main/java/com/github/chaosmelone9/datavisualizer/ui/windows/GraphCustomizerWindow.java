// SPDX-License-Identifier: GPL-3.0-or-later
/*
 *  DataVisualizer
 *  Copyright (C) 2022 Christoph Kohnen <christoph.kohnen@gymbane.eu>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph.Graph;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GraphCustomizerWindow extends PopupWindow{
    Graph graph;
    public GraphCustomizerWindow(MainWindow window) {
        super("Customize Graph", window);
        this.graph = window.getContentPane().getGraph();

        //Checkboxes
        add(new JLabel("Select Components"));
        add(new CheckBox("Draw x grid", graph.isDrawXGrid(), true, actionEvent -> graph.setDrawXGrid(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw y grid", graph.isDrawYGrid(), true, actionEvent -> graph.setDrawYGrid(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw x hatch marks of bottom x-axis", graph.isDrawXAHatchMarks(), true, actionEvent -> graph.setDrawXAHatchMarks(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw x hatch marks of top x-axis", graph.isDrawXBHatchMarks(), true, actionEvent -> graph.setDrawXBHatchMarks(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw hatch marks of left y-axis", graph.isDrawYAHatchMarks(), true, actionEvent -> graph.setDrawYAHatchMarks(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw hatch marks of right y-axis", graph.isDrawYBHatchMarks(), graph.hasSecondYAxis(), actionEvent -> graph.setDrawYBHatchMarks(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw x labels of bottom x-axis", graph.isDrawXALabels(), true, actionEvent -> graph.setDrawXALabels(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw x labels of top x-axis", graph.isDrawXBLabels(), graph.hasSecondXAxis(), actionEvent -> graph.setDrawXBLabels(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw y labels of left y-axis", graph.isDrawYALabels(), true, actionEvent -> graph.setDrawYALabels(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Draw y labels of right y-axis", graph.isDrawYBLabels(), graph.hasSecondYAxis(), actionEvent -> graph.setDrawYBLabels(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Indicate x position of mouse-pointer", graph.isIndicateMouseX(), true, actionEvent -> graph.setIndicateMouseX(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Indicate y position of mouse-pointer", graph.isIndicateMouseY(), true, actionEvent -> graph.setIndicateMouseY(((CheckBox) actionEvent.getSource()).isSelected())));
        add(new CheckBox("Label x and y position of mouse-pointer", graph.isLabelMouseXY(), true, actionEvent -> graph.setLabelMouseXY(((CheckBox) actionEvent.getSource()).isSelected())));

        //Color pickers
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(new JLabel("Colors"));
        add(new ColorPicker("Background Color", actionEvent -> graph.setBackgroundColour(JColorChooser.showDialog(this, "Choose Colour", graph.getBackgroundColour()))));
        add(new ColorPicker("Grid Color", actionEvent -> graph.setGridColour(JColorChooser.showDialog(this, "Choose Colour", graph.getGridColour()))));
        add(new ColorPicker("Label Color", actionEvent -> graph.setLabelColour(JColorChooser.showDialog(this, "Choose Colour", graph.getLabelColour()))));
        add(new ColorPicker("Title Color", actionEvent -> graph.setTitleColour(JColorChooser.showDialog(this, "Choose Colour", graph.getTitleColour()))));
        add(new ColorPicker("Axis Color", actionEvent -> graph.setAxisColour(JColorChooser.showDialog(this, "Choose Colour", graph.getAxisColour()))));
        add(new ColorPicker("Hatch mark Color", actionEvent -> graph.setHatchMarkColour(JColorChooser.showDialog(this, "Choose Colour", graph.getHatchMarkColour()))));
        add(new ColorPicker("Indicator Color", actionEvent -> graph.setIndicatorColour(JColorChooser.showDialog(this, "Choose Colour", graph.getIndicatorColour()))));

        add(new JLabel("Background Image"));
        JButton backgroundImageButton = new JButton("Background Image");
        backgroundImageButton.addActionListener(actionEvent -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new java.io.File("."));
            fileChooser.setDialogTitle("Choose Background Image");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    graph.setBackgroundImage(ImageIO.read(fileChooser.getSelectedFile()));
                } catch (IOException e) {
                    mainWindow.getInstance().getLogger().logStackTrace(e);
                }
            }
        });
        add(backgroundImageButton);
    }

    private static class CheckBox extends JCheckBox {
        public CheckBox(String name, boolean selected, boolean enabled, ActionListener actionListener) {
            super(name);
            setSelected(selected);
            setEnabled(enabled);
            addActionListener(actionListener);
        }
    }

    private static class ColorPicker extends JButton {
        public ColorPicker(String name, ActionListener actionListener) {
            super(name);
            addActionListener(actionListener);
        }
    }
}
