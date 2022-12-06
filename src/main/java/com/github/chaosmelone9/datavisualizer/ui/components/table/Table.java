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
package com.github.chaosmelone9.datavisualizer.ui.components.table;

import com.github.chaosmelone9.datavisualizer.ui.GraphData.GraphDataSet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Table extends JPanel {
    protected final ButtonPanel buttonPanel;
    protected final TablePanel tablePanel;
    protected List<CellSelectedListener> cellSelectedListeners = new ArrayList<>();

    public Table(GraphDataSet dataSet) {
        super();
        setLayout(new BorderLayout());
        this.buttonPanel = new ButtonPanel(this);
        this.tablePanel = new TablePanel(this, dataSet);
        add(buttonPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    public void addCellSelectedListener(CellSelectedListener listener) {
        cellSelectedListeners.add(listener);
    }

    public void removeCellSelectedListener(CellSelectedListener listener) {
        cellSelectedListeners.remove(listener);
    }
}
