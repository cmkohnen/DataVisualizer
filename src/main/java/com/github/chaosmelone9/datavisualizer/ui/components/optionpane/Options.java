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

import com.github.chaosmelone9.datavisualizer.ui.Adwaita;
import com.github.chaosmelone9.datavisualizer.ui.components.GraphData.GraphDataChangeListener;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.GraphObject;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Options extends JPanel {
    public Options(MainWindow window) {
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        add(new JLabel("Example Text"), constraints);
        setBackground(Adwaita.BLUE5);

        window.getGraphDataSet().addListener((type, object) -> {
            constraints.gridy++;
            add(new GraphObjectCustomizer(object), constraints);
        });
    }
}
