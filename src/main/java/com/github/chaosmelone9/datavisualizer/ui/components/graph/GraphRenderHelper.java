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
