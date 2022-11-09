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
package com.github.chaosmelone9.datavisualizer.ui.components.graph;

import com.github.chaosmelone9.datavisualizer.datasets.Oval;
import com.github.chaosmelone9.datavisualizer.datasets.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphOval extends GraphObject {
    protected Oval oval;
    protected boolean filled;

    public GraphOval(Oval oval, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour, boolean filled, boolean visible) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, colour, visible);
        this.type = Type.GRAPHOVAL;
        this.oval = oval;
        this.filled = filled;
    }

    @Override
    protected boolean isInRange(double minX, double minY, double maxX, double maxY) {
        List<Point> list = new ArrayList<>();
        double ovalMinX = oval.center.x - oval.xHeight;
        double ovalMaxX = oval.center.x + oval.xHeight;
        double ovalMinY = oval.center.y - oval.yHeight;
        double ovalMaxY = oval.center.y + oval.yHeight;
        list.add(new Point(ovalMinX, ovalMinY));
        list.add(new Point(ovalMinX, ovalMaxY));
        list.add(new Point(ovalMaxX, ovalMinY));
        list.add(new Point(ovalMaxX, ovalMaxY));
        list.add(oval.center);
        boolean isInRange = false;
        for (Point point : list) {
            if(isPointInRange(minX, minY, maxX, maxY, point)) {
                isInRange = true;
            }
        }
        if(ovalMaxX > maxX && ovalMinX < minX && ovalMaxY > maxY && ovalMinY < minY) {
            isInRange = true;
        }
        return isInRange;
    }
}
