package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.Graph;

import javax.swing.*;
import java.awt.*;
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

    private int padding = 25;
    private int labelPadding = 25;

    private int pointWidth = 4;
    private int numberYDivisions = 100;
    private int numberXDivisions = 100;

    private boolean drawXGrid = true;
    private boolean drawYGrid = true;
    private boolean drawXHatchMarks = true;
    private boolean drawYAHatchMarks = true;
    private boolean drawYBHatchMarks = true;
    private boolean drawXLabels = true;
    private boolean drawYALabels = true;
    private boolean drawYBLabels = true;

    Color backgroundColour = new Color(255, 255, 255);
    Color gridColour = new Color(0,0,0);
    Color labelColour = new Color(255,255,255);
    Color axisColour = new Color(218, 7, 7);
    Color hatchMarkColour = new Color(19, 145, 21);

    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);

    public Graph() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //setup rendering
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(GRAPH_STROKE);

        //figuring out dimensions of the graph
        int startX = padding + labelPadding;
        int startY = padding;
        int stopX = getWidth() - (padding * 2) - labelPadding;
        int stopY = getHeight() - (padding * 2) - labelPadding;
        if(secondYAxis()) {
            stopX = stopX - labelPadding;
        }

        //paint background
        g2.setColor(backgroundColour);
        g2.fillRect(startX, startY, stopX, stopY);

        /// create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0A = startX;
            int x1A = startX + pointWidth;
            int x0B = stopX + labelPadding + padding;
            int x1B = x0B - pointWidth;
            int y0 = getHeight() - ((i * stopY) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if(i % 10 == 0) {
                if(drawYGrid) {
                    g2.setColor(gridColour);
                    g2.drawLine(startX + 1 + pointWidth, y0, stopX + padding + labelPadding, y1);
                }
                if(drawYALabels || drawYBLabels) {
                    g2.setColor(labelColour);
                    FontMetrics metrics = g2.getFontMetrics();
                    if(drawYALabels) {
                        String yALabel = ((int) ((minYA() + (maxYA() - minYA()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                        int labelWidth = metrics.stringWidth(yALabel);
                        g2.drawString(yALabel, x0A - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
                    }
                    if(secondYAxis() && drawYBLabels) {
                        String yBLabel = ((int) ((minYB() + (maxYB() - minYB()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                        g2.drawString(yBLabel, x0B + 5, y0 + (metrics.getHeight() / 2) - 3);
                    }
                }
            }
            if(drawYAHatchMarks || drawYBHatchMarks) {
                g2.setColor(hatchMarkColour);
                if(drawYAHatchMarks) {
                    g2.drawLine(x0A, y0, x1A, y1);
                }
                if(secondYAxis() && drawYBHatchMarks) {
                    g2.drawLine(x0B, y0, x1B, y1);
                }
            }
        }

        // and for x axis
        for (int i = 0; i < numberXDivisions + 1; i++) {
            int x0 = i * stopX / numberXDivisions + padding + labelPadding;
            int x1 = x0;
            int y0 = stopY + padding;
            int y1 = stopY + padding - pointWidth;
            if(i % 10 == 0) {
                if(drawXGrid) {
                    g2.setColor(gridColour);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                }
                if(drawXLabels) {
                    g2.setColor(labelColour);
                    String xLabel = ((int) ((minX() + (maxX() - minX()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
            }
            if(drawXHatchMarks) {
                g2.setColor(hatchMarkColour);
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes
        g2.setColor(axisColour);
        g2.drawLine(startX, startY, startX, stopY + padding);
        if(secondYAxis()) {
            g2.drawLine(stopX + padding + labelPadding, startY, stopX + padding + labelPadding, stopY + padding);
        }
        g2.drawLine(startX, stopY + padding, stopX + padding + labelPadding, stopY + padding);
    }

    private boolean secondYAxis() {
        return false;
    }

    private double minX() {
        return -20;
    }

    private double maxX() {
        return 100;
    }

    private double minYA() {
        return -20;
    }

    private double minYB() {
        return -40;
    }

    private double maxYA() {
        return 100;
    }

    private double maxYB() {
        return 200;
    }
}
