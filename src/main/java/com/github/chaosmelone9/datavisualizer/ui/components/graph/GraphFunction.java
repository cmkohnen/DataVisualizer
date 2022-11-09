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

import java.awt.*;
import java.util.function.Function;

public class GraphFunction extends GraphObject {
    protected Function<Double, Double> function;

    public GraphFunction(Function<Double, Double> function, boolean allocateToSecondXAxis, boolean allocateToSecondYAxis, Color colour, boolean visible) {
        super(allocateToSecondXAxis, allocateToSecondYAxis, colour, visible);
        this.type = Type.GRAPHFUNCTION;
        this.function = function;
    }

    @Override
    protected boolean isInRange(double minX, double minY, double maxX, double maxY) {
        return true;
    }
}
