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

import com.github.chaosmelone9.datavisualizer.ui.components.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class GraphZoomWindow extends PopupWindow {
    public GraphZoomWindow(MainWindow window, Graph graph) {
        super("Zoom", window);
        Dimension spinnerDimension = new Dimension(60, 25);
        JSpinner minXA = new JSpinner(new SpinnerNumberModel(graph.getMinXA(), null, null, 0.1));
        minXA.addChangeListener(e -> {
            graph.setMinXA((double) minXA.getValue());
            graph.repaint();
        });
        minXA.setPreferredSize(spinnerDimension);
        JSpinner maxXA = new JSpinner(new SpinnerNumberModel(graph.getMaxXA(), null, null, 0.1));
        maxXA.addChangeListener(e -> {
            graph.setMaxXA((double) maxXA.getValue());
            graph.repaint();
        });
        maxXA.setPreferredSize(spinnerDimension);
        JSpinner minXB = new JSpinner(new SpinnerNumberModel(graph.getMinXB(), null, null, 0.1));
        minXB.addChangeListener(e -> {
            graph.setMinXB((double) minXB.getValue());
            graph.repaint();
        });
        minXB.setPreferredSize(spinnerDimension);
        JSpinner maxXB = new JSpinner(new SpinnerNumberModel(graph.getMaxXB(), null, null, 0.1));
        maxXB.addChangeListener(e -> {
            graph.setMaxXB((double) maxXB.getValue());
            graph.repaint();
        });
        maxXB.setPreferredSize(spinnerDimension);
        JSpinner minYA = new JSpinner(new SpinnerNumberModel(graph.getMinYA(), null, null, 0.1));
        minYA.addChangeListener(e -> {
            graph.setMinYA((double) minYA.getValue());
            graph.repaint();
        });
        minYA.setPreferredSize(spinnerDimension);
        JSpinner maxYA = new JSpinner(new SpinnerNumberModel(graph.getMaxYA(), null, null, 0.1));
        maxYA.addChangeListener(e -> {
            graph.setMaxYA((double) maxYA.getValue());
            graph.repaint();
        });
        maxYA.setPreferredSize(spinnerDimension);
        JSpinner minYB = new JSpinner(new SpinnerNumberModel(graph.getMinYB(), null, null, 0.1));
        minYB.addChangeListener(e -> {
            graph.setMinYB((double) minYB.getValue());
            graph.repaint();
        });
        minYB.setPreferredSize(spinnerDimension);
        JSpinner maxYB = new JSpinner(new SpinnerNumberModel(graph.getMaxYB(), null, null, 0.1));
        maxYB.addChangeListener(e -> {
            graph.setMaxYB((double) maxYB.getValue());
            graph.repaint();
        });
        maxYB.setPreferredSize(spinnerDimension);

        boolean xB = graph.hasSecondXAxis();
        boolean yB = graph.hasSecondYAxis();

        minXB.setEnabled(xB);
        maxXB.setEnabled(xB);
        minYB.setEnabled(yB);
        maxYB.setEnabled(yB);

        add(new JLabel("min. X (1st axis)"));
        add(new JLabel("min. X (2nd axis)"));
        add(new JLabel("min. Y (1st axis)"));
        add(new JLabel("min. Y (2nd axis)"));
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(minXA);
        add(minXB);
        add(minYA);
        add(minYB);
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(new JLabel("max. X (1st axis)"));
        add(new JLabel("max. X (2nd axis)"));
        add(new JLabel("max. Y (1st axis)"));
        add(new JLabel("max. Y (2nd axis)"));
        constraints.gridx = 3;
        constraints.gridy = 0;
        add(maxXA);
        add(maxXB);
        add(maxYA);
        add(maxYB);

        setResizable(false);
        this.setSize(350, 200);
    }
}
