package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import com.github.chaosmelone9.datavisualizer.config.GraphConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author "Christoph Kohnen", "Hovercraft Full of Eels", "Rodrigo Azevedo"
 *
 * The Base of this Component is an improved version of Hovercraft Full of Eels (https://stackoverflow.com/users/522444/hovercraft-full-of-eels)
 * answer on StackOverflow: https://stackoverflow.com/a/8693635/753012 by Rodrigo Azevedo. Huge credits to them for figuring out the maths behind this.
 * However, this is heavily modified to include e.g. multiple rows, mathematical functions two y-axes, etc.
 */
public class Graph extends JPanel {

    List<Row> rows = new ArrayList<>();
    List<Function> functions = new ArrayList<>();

    private final GraphConfig config;

    private int mouseX;
    private int mouseY;

    public Graph(GraphConfig config) {
        super();
        this.config = config;

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        final int padding = config.padding;
        final int labelPadding = config.labelPadding;
        final int titlePadding = config.titlePadding;

        final int pointWidth = config.pointWidth;
        final int numberYDivisions = config.numberYDivisions;
        final int numberXDivisions = config.numberXDivisions;

        final boolean drawTitle = config.drawTitle;
        final boolean drawXGrid = config.drawXGrid;
        final boolean drawYGrid = config.drawYGrid;
        final boolean drawXHatchMarks = config.drawXHatchMarks;
        final boolean drawYAHatchMarks = config.drawYAHatchMarks;
        final boolean drawYBHatchMarks = config.drawYBHatchMarks;
        final boolean drawXLabels = config.drawXLabels;
        final boolean drawYALabels = config.drawYALabels;
        final boolean drawYBLabels = config.drawYBLabels;
        final boolean indicateMouseX = config.indicateMouseX;
        final boolean indicateMouseY = config.indicateMouseY;

        final Color backgroundColour = config.backgroundColour;
        final Color gridColour = config.gridColour;
        final Color labelColour = config.labelColour;
        final Color titleColour = config.titleColour;
        final Color axisColour = config.axisColour;
        final Color hatchMarkColour = config.hatchMarkColour;
        final Color indicatorColour = config.indicatorColour;

        final String title = config.title;

        //setup rendering
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1f));
        FontMetrics metrics = g2.getFontMetrics();

        //figuring out dimensions of the graph
        int startX = padding + labelPadding;
        int startY = padding;
        int stopX = getWidth() - padding - labelPadding;
        int stopY = getHeight() - padding - labelPadding;
        if(secondYAxis()) {
            stopX = stopX - labelPadding;
        }
        double xScale = ((double) stopX - padding) / (maxX() - minX());
        double yAScale = ((double) stopY - padding) / (maxYA() - minYA());
        double yBScale = ((double) stopY - padding) / (maxYB() - minYB());

        //draw Title
        if(drawTitle) {
            startY = startY + titlePadding;
            g2.setColor(titleColour);
            g2.drawString(title,(getWidth() / 2) - (metrics.stringWidth(title) / 2), padding);
        }

        //paint background
        g2.setColor(backgroundColour);
        if(drawTitle) {
            g2.fillRect(startX, startY, stopX - padding, stopY  - padding - titlePadding);
        } else {
            g2.fillRect(startX, startY, stopX - padding, stopY - padding);
        }

        /// create hatch marks and grid lines for y-axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x1A = startX + pointWidth;
            int x0B = stopX + labelPadding;
            int x1B = x0B - pointWidth;
            int y = stopY - ((i * (stopY - startY)) / numberYDivisions);
            if(i % 10 == 0) {
                if(drawYGrid) {
                    g2.setColor(gridColour);
                    g2.drawLine(startX, y, stopX + labelPadding, y);
                }
                if(drawYALabels || drawYBLabels) {
                    g2.setColor(labelColour);
                    if(drawYALabels) {
                        String yALabel = ((int) ((minYA() + (maxYA() - minYA()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                        int labelWidth = metrics.stringWidth(yALabel);
                        g2.drawString(yALabel, startX - labelWidth - 5, y + (metrics.getHeight() / 2) - 3);
                    }
                    if(secondYAxis() && drawYBLabels) {
                        String yBLabel = ((int) ((minYB() + (maxYB() - minYB()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                        g2.drawString(yBLabel, x0B + 5, y + (metrics.getHeight() / 2) - 3);
                    }
                }
            } else if(drawYAHatchMarks || drawYBHatchMarks) {
                g2.setColor(hatchMarkColour);
                if(drawYAHatchMarks) {
                    g2.drawLine(startX, y, x1A, y);
                }
                if(secondYAxis() && drawYBHatchMarks) {
                    g2.drawLine(x0B, y, x1B, y);
                }
            }
        }

        // and for x-axis
        for (int i = 0; i < numberXDivisions + 1; i++) {
            int x = i * (stopX - padding) / numberXDivisions + padding + labelPadding;
            if(i % 10 == 0) {
                if(drawXGrid) {
                    g2.setColor(gridColour);
                    g2.drawLine(x, stopY, x, startY);
                }
                if(drawXLabels) {
                    g2.setColor(labelColour);
                    String xLabel = ((int) ((minX() + (maxX() - minX()) * ((i * 1.0) / numberXDivisions)) * 100)) / 100.0 + "";
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x - labelWidth / 2, stopY + metrics.getHeight() + 3);
                }
            } else if(drawXHatchMarks) {
                g2.setColor(hatchMarkColour);
                g2.drawLine(x, stopY, x, stopY - pointWidth);
            }
        }

        // create x and y axes
        g2.setColor(axisColour);
        g2.drawLine(startX, startY, startX, stopY);
        if(secondYAxis()) {
            g2.drawLine(stopX + labelPadding, startY, stopX + labelPadding, stopY);
        }
        g2.drawLine(startX, stopY, stopX + labelPadding, stopY);

        //draw x and y axes at 0
        if(minX() < 0 && maxX() > 0) {
            int x = (int) Math.abs(minX() * xScale) + startX;
            g2.drawLine(x, startY, x, stopY);
        }
        if(minYA() < 0 && maxYA() > 0) {
            int y = (int) (stopY - Math.abs(minYA() * yAScale));
            g2.drawLine(startX, y, stopX + padding, y);
        }
        if(secondYAxis() && (minYB() < 0 && maxYB() > 0)) {
            int y = (int) (stopY - Math.abs(minYB() * yBScale));
            g2.drawLine(startX, y, stopX + padding, y);
        }

        //draw X and Y indication at Mouse-pointer
        if((indicateMouseX || indicateMouseY) && (mouseX >= startX && mouseX <= stopX + padding && mouseY >= startY && mouseY <= stopY)) {
            g2.setColor(indicatorColour);
            if(indicateMouseX) {
                g2.drawLine(startX, mouseY, stopX + padding, mouseY);
            }
            if(indicateMouseY) {
                g2.drawLine(mouseX, startY, mouseX, stopY);
            }
        }

        //render rows and functions
        //TODO actually render them
        g2.setStroke(new BasicStroke(2f));

        //cleanup
        g2.dispose();
        g.dispose();
    }

    private boolean secondYAxis() {
        if(rows.isEmpty() & functions.isEmpty()) {
            return false;
        } else {
            boolean result = false;
            for (Row row : rows) {
                if (false) { //TODO implement stuff
                    result = true;
                    break;
                }
            }
            for (Function function : functions) {
                if (false) { //TODO implement stuff
                    result = true;
                    break;
                }
            }
            return result;
        }
    }

    private double minX() {
        return -100;
    }

    private double maxX() {
        return 10;
    }

    private double minYA() {
        return -100;
    }

    private double minYB() {
        return -20;
    }

    private double maxYA() {
        return 10;
    }

    private double maxYB() {
        return 200;
    }

    public GraphConfig getConfig() {
        return config;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
