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

import com.github.chaosmelone9.datavisualizer.datasets.Point;
import com.github.chaosmelone9.datavisualizer.datasets.Polygon;

import java.awt.*;

public class GraphPolygon extends GraphObject {
    public Polygon polygon;
    public boolean filled;

    public GraphPolygon(
            Polygon polygon,
            String name,
            boolean allocateToSecondXAxis,
            boolean allocateToSecondYAxis,
            Color colour,
            boolean filled,
            boolean visible) {
        super(name, allocateToSecondXAxis, allocateToSecondYAxis, colour, visible);
        this.type = Type.GRAPHPOLYGON;
        this.polygon = polygon;
        this.filled = filled;
    }

    @Override
    public boolean isInRange(double minX, double minY, double maxX, double maxY) {
        boolean isInRange = false;
        for (Point point : polygon.points) {
            if (isPointInRange(minX, minY, maxX, maxY, point)) {
                isInRange = true;
                break;
            }
        }
        return isInRange;
    }
}
