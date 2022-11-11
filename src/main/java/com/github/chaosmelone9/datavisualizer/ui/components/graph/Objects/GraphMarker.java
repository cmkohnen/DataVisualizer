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
package com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects;

import java.awt.*;

public class GraphMarker extends GraphObject {
    public boolean xOrY;
    public double value;

    public GraphMarker(boolean xOrY, double value, String name, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour, boolean visible) {
        super(name, allocateToSecondXAxis, allocateToSecondYAxis, colour, visible);
        this.type = Type.GRAPHMARKER;
        this.xOrY = xOrY;
        this.value = value;
    }

    @Override
    public boolean isInRange(double minX, double minY, double maxX, double maxY) {
        if(xOrY) {
            return minX < value && maxX > value;
        } else {
            return minY < value && maxY > value;
        }
    }
}
