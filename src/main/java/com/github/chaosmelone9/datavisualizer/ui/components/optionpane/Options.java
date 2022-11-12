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
package com.github.chaosmelone9.datavisualizer.ui.components.optionpane;

import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.GraphObject;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Options extends JPanel {
    public Options(MainWindow window) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JComboBox<GraphObject> selector = new JComboBox<>();
        selector.setMaximumSize(new Dimension(200, 30));
        add(selector);

        GraphObjectCustomizer customizer = new GraphObjectCustomizer(window, null);
        add(customizer);

        selector.addActionListener(actionEvent -> {
            customizer.setObject((GraphObject) Objects.requireNonNull(selector.getSelectedItem()));
            repaint();
        });

        window.getGraphDataSet().addListener((type, object) -> {
            switch (type) {
                case ADD -> selector.addItem(object);
                case REMOVE -> selector.removeItem(object);
            }
        });
    }
}
