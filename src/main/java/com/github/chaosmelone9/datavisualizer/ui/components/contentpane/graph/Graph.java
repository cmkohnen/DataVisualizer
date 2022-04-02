package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

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

    private int padding;
    private int labelPadding;
    private int titlePadding;

    private int pointWidth;
    private int numberYDivisions;
    private int numberXDivisions;

    private boolean drawTitle;
    private boolean drawXGrid;
    private boolean drawYGrid;
    private boolean drawXHatchMarks;
    private boolean drawYAHatchMarks;
    private boolean drawYBHatchMarks;
    private boolean drawXLabels;
    private boolean drawYALabels;
    private boolean drawYBLabels;
    private boolean indicateMouseX;
    private boolean indicateMouseY;

    private Color backgroundColour;
    private Color gridColour;
    private Color labelColour;
    private Color titleColour;
    private Color axisColour;
    private Color hatchMarkColour;
    private Color indicatorColour;

    String title;

    private int mouseX;
    private int mouseY;

    public Graph() {
        super();

        this.title = "Title";

        this.padding = 25;
        this.labelPadding = 25;
        this.titlePadding = 30;

        this.pointWidth = 4;
        this.numberXDivisions = 100;
        this.numberYDivisions = 100;

        this.drawTitle = false;
        this.drawXGrid = true;
        this.drawYGrid = true;
        this.drawXHatchMarks = true;
        this.drawYAHatchMarks = true;
        this.drawYBHatchMarks = true;
        this.drawXLabels = true;
        this.drawYALabels = true;
        this.drawYBLabels = true;
        this.indicateMouseX = true;
        this.indicateMouseY = true;

        this.backgroundColour = new Color(255, 255, 255);
        this.gridColour = new Color(0,0,0);
        this.labelColour = new Color(255,255,255);
        this.titleColour = new Color(255,255,255);
        this.axisColour = new Color(218, 7, 7);
        this.hatchMarkColour = new Color(19, 145, 21);
        this.indicatorColour = new Color(22,54,122);

        if(indicateMouseX || indicateMouseY) {
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        //setup rendering
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Stroke GRAPH_STROKE = new BasicStroke(2f);
        g2.setStroke(GRAPH_STROKE);
        FontMetrics metrics = g2.getFontMetrics();

        //figuring out dimensions of the graph
        int startX = padding + labelPadding;
        int startY = padding;
        int stopX = getWidth() - padding - labelPadding;
        int stopY = getHeight() - padding - labelPadding;
        if(secondYAxis()) {
            stopX = stopX - labelPadding;
        }

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
                    g2.drawLine(startX + 1 + pointWidth, y, stopX + labelPadding, y);
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
            }
            if(drawYAHatchMarks || drawYBHatchMarks) {
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
            int y = stopY - pointWidth;
            if(i % 10 == 0) {
                if(drawXGrid) {
                    g2.setColor(gridColour);
                    g2.drawLine(x, stopY - 1 - pointWidth, x, startY);
                }
                if(drawXLabels) {
                    g2.setColor(labelColour);
                    String xLabel = ((int) ((minX() + (maxX() - minX()) * ((i * 1.0) / numberXDivisions)) * 100)) / 100.0 + "";
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x - labelWidth / 2, stopY + metrics.getHeight() + 3);
                }
            }
            if(drawXHatchMarks) {
                g2.setColor(hatchMarkColour);
                g2.drawLine(x, stopY, x, y);
            }
        }

        // create x and y axes
        g2.setColor(axisColour);
        g2.drawLine(startX, startY, startX, stopY);
        if(secondYAxis()) {
            g2.drawLine(stopX + labelPadding, startY, stopX + labelPadding, stopY);
        }
        g2.drawLine(startX, stopY, stopX + labelPadding, stopY);

        //draw X and Y indication at Mouse-pointer
        if((indicateMouseX || indicateMouseY) && (mouseX >= startX && mouseX <= stopX && mouseY >= startY && mouseY <= stopY)) {
            g2.setColor(indicatorColour);
            if(indicateMouseX) {
                g2.drawLine(startX, mouseY, stopX + padding, mouseY);
            }
            if(indicateMouseY) {
                g2.drawLine(mouseX, startY, mouseX, stopY);
            }
        }

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



    public void setPadding(int i) {
        this.padding = i;
    }

    public void setDrawTitle(boolean drawTitle) {
        this.drawTitle = drawTitle;
    }

    public boolean isDrawTitle() {
        return drawTitle;
    }

    public boolean isDrawXGrid() {
        return drawXGrid;
    }

    public boolean isDrawXHatchMarks() {
        return drawXHatchMarks;
    }

    public boolean isDrawXLabels() {
        return drawXLabels;
    }

    public boolean isDrawYAHatchMarks() {
        return drawYAHatchMarks;
    }

    public boolean isDrawYALabels() {
        return drawYALabels;
    }

    public boolean isDrawYBHatchMarks() {
        return drawYBHatchMarks;
    }

    public boolean isDrawYGrid() {
        return drawYGrid;
    }

    public boolean isDrawYBLabels() {
        return drawYBLabels;
    }

    public boolean isIndicateMouseX() {
        return indicateMouseX;
    }

    public boolean isIndicateMouseY() {
        return indicateMouseY;
    }

    public Color getBackgroundColour() {
        return backgroundColour;
    }

    public int getLabelPadding() {
        return labelPadding;
    }

    public Color getAxisColour() {
        return axisColour;
    }

    public int getNumberXDivisions() {
        return numberXDivisions;
    }

    public Color getGridColour() {
        return gridColour;
    }

    public int getNumberYDivisions() {
        return numberYDivisions;
    }

    public Color getHatchMarkColour() {
        return hatchMarkColour;
    }

    public Color getIndicatorColour() {
        return indicatorColour;
    }

    public Color getLabelColour() {
        return labelColour;
    }

    public Color getTitleColour() {
        return titleColour;
    }

    public int getPadding() {
        return padding;
    }

    public int getPointWidth() {
        return pointWidth;
    }

    public int getTitlePadding() {
        return titlePadding;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setBackgroundColour(Color backgroundColour) {
        this.backgroundColour = backgroundColour;
    }

    public String getTitle() {
        return title;
    }

    public void setDrawXGrid(boolean drawXGrid) {
        this.drawXGrid = drawXGrid;
    }

    public void setDrawXHatchMarks(boolean drawXHatchMarks) {
        this.drawXHatchMarks = drawXHatchMarks;
    }

    public void setDrawXLabels(boolean drawXLabels) {
        this.drawXLabels = drawXLabels;
    }

    public void setDrawYAHatchMarks(boolean drawYAHatchMarks) {
        this.drawYAHatchMarks = drawYAHatchMarks;
    }

    public void setAxisColour(Color axisColour) {
        this.axisColour = axisColour;
    }

    public void setDrawYALabels(boolean drawYALabels) {
        this.drawYALabels = drawYALabels;
    }

    public void setDrawYBHatchMarks(boolean drawYBHatchMarks) {
        this.drawYBHatchMarks = drawYBHatchMarks;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setDrawYGrid(boolean drawYGrid) {
        this.drawYGrid = drawYGrid;
    }

    public void setDrawYBLabels(boolean drawYBLabels) {
        this.drawYBLabels = drawYBLabels;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public void setIndicateMouseX(boolean indicateMouseX) {
        this.indicateMouseX = indicateMouseX;
    }

    public void setIndicateMouseY(boolean indicateMouseY) {
        this.indicateMouseY = indicateMouseY;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void setGridColour(Color gridColour) {
        this.gridColour = gridColour;
    }

    public void setHatchMarkColour(Color hatchMarkColour) {
        this.hatchMarkColour = hatchMarkColour;
    }

    public void setLabelColour(Color labelColour) {
        this.labelColour = labelColour;
    }

    public void setIndicatorColour(Color indicatorColour) {
        this.indicatorColour = indicatorColour;
    }

    public void setLabelPadding(int labelPadding) {
        this.labelPadding = labelPadding;
    }

    public void setNumberXDivisions(int numberXDivisions) {
        this.numberXDivisions = numberXDivisions;
    }

    public void setNumberYDivisions(int numberYDivisions) {
        this.numberYDivisions = numberYDivisions;
    }

    public void setPointWidth(int pointWidth) {
        this.pointWidth = pointWidth;
    }

    public void setTitleColour(Color titleColour) {
        this.titleColour = titleColour;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitlePadding(int titlePadding) {
        this.titlePadding = titlePadding;
    }
}
