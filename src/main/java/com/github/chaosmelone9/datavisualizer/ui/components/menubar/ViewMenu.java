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
