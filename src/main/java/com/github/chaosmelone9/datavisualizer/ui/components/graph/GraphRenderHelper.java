// SPDX-License-Identifier: GPL-3.0-or-later
/*
 *  DataVisualizer
 *  Copyright (C) 2022 Christoph Kohnen <christoph.kohnen@gymbane.eu>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
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

public class GraphRenderHelper {
    private final Graphics2D graphics2D;
    protected GraphRenderHelper(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    protected void fillRect(Rectangle rectangle) {
        graphics2D.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    protected void drawRect(Rectangle rectangle) {
        graphics2D.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    protected void drawBorderedRect(Rectangle rectangle, Color colour, Color border) {
        graphics2D.setColor(colour);
        this.fillRect(rectangle);
        graphics2D.setColor(border);
        this.drawRect(rectangle);
    }
}
