package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
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
    List<GraphFunction> graphFunctions = new ArrayList<>();

    private String title;

    private int padding;
    private int labelPadding;
    private int titlePadding;

    private int pointWidth;
    private int numberXDivisions;
    private int numberYDivisions;

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
    private boolean labelMouseXY;

    private Color backgroundColour;
    private Color gridColour;
    private Color labelColour;
    private Color titleColour;
    private Color axisColour;
    private Color hatchMarkColour;
    private Color indicatorColour;

    private BufferedImage backgroundImage;

    private Stroke graphStroke;
    private Stroke uiStroke;

    private double minX;
    private double maxX;
    private double minYA;
    private double minYB;
    private double maxYA;
    private double maxYB;

    private boolean hasSecondYAxis = false;
    private boolean hasTitle = false;
    private boolean hasBackgroundImage = false;

    private int mouseX;
    private int mouseY;

    private int startX;
    private int stopX;
    private int startY;
    private int stopY;

    private double xScale;
    private double yAScale;
    private double yBScale;

    public Graph() {
        super();

        //TODO load from config
        this.title = null;

        this.padding = 20;
        this.labelPadding = 100;
        this.titlePadding = 30;

        this.pointWidth = 4;
        this.numberXDivisions = 100;
        this.numberYDivisions = 100;

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
        this.labelMouseXY = true;

        this.backgroundColour = new Color(255, 255, 255);
        this.gridColour = new Color(0,0,0);
        this.labelColour = new Color(255,255,255);
        this.titleColour = new Color(255,255,255);
        this.axisColour = new Color(218, 7, 7);
        this.hatchMarkColour = new Color(19, 145, 21);
        this.indicatorColour = new Color(22,54,122);

        this.backgroundImage = null;

        this.graphStroke = new BasicStroke(2f);
        this.uiStroke = new BasicStroke(1f);

        this.minX = -10;
        this.maxX = 10;
        this.minYA = -10;
        this.minYB = -20;
        this.maxYA = 100;
        this.maxYB = 400;

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
                repaint();
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
        try {
            //setup rendering
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(uiStroke);
            FontMetrics metrics = g2.getFontMetrics();

            //figuring out dimensions of the graph
            this.startX = padding + labelPadding;
            this.startY = padding;
            if(hasTitle) {
                this.startY = startY + titlePadding;
            }
            this.stopX = getWidth() - padding;
            this.stopY = getHeight() - padding - labelPadding;
            if(hasSecondYAxis) {
                this.stopX = stopX - labelPadding;
            }
            this.xScale = (stopX - startX) / (maxX - minX);
            this.yAScale = (stopY - startY) / (maxYA - minYA);
            this.yBScale = (stopY - startY) / (maxYB - minYB);

            //figure out where 0s are
            int zeroX = (int) Math.abs(minX * xScale) + startX;
            int zeroYA = (int) (stopY - Math.abs(minYA * yAScale));
            int zeroYB = (int) (stopY - Math.abs(minYB * yBScale));

            //draw Title
            if(hasTitle) {
                g2.setColor(titleColour);
                g2.drawString(title,(getWidth() / 2) - (metrics.stringWidth(title) / 2), padding);
            }

            //paint background
            g2.setColor(backgroundColour);
            int backgroundWidth = stopX - startX;
            int backgroundHeight = stopY - startY;
            g2.fillRect(startX, startY, backgroundWidth, backgroundHeight);
            if(hasBackgroundImage) {
                g2.drawImage(backgroundImage, startX, startY, backgroundWidth, backgroundHeight, this);
            }

            /// create hatch marks and grid lines for y-axis.
            for (int i = 0; i < numberYDivisions + 1; i++) {
                int y = stopY - ((i * (stopY - startY)) / numberYDivisions);
                if(i % 10 == 0) {
                    if(drawYGrid) {
                        g2.setColor(gridColour);
                        g2.drawLine(startX, y, stopX, y);
                    }
                    if(drawYALabels || drawYBLabels) {
                        g2.setColor(labelColour);
                        if(drawYALabels) {
                            String yALabel = ((int) ((minYA + (maxYA - minYA) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                            int labelWidth = metrics.stringWidth(yALabel);
                            g2.drawString(yALabel, startX - labelWidth - 5, y + (metrics.getHeight() / 2) - 3);
                        }
                        if(hasSecondYAxis && drawYBLabels) {
                            String yBLabel = ((int) ((minYB + (maxYB - minYB) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                            g2.drawString(yBLabel, stopX + 5, y + (metrics.getHeight() / 2) - 3);
                        }
                    }
                } else if(drawYAHatchMarks || drawYBHatchMarks) {
                    g2.setColor(hatchMarkColour);
                    if(drawYAHatchMarks) {
                        g2.drawLine(startX, y, startX + pointWidth, y);
                    }
                    if(hasSecondYAxis && drawYBHatchMarks) {
                        g2.drawLine(stopX, y, stopX - pointWidth, y);
                    }
                }
            }

            // and for x-axis
            for (int i = 0; i < numberXDivisions + 1; i++) {
                int x = i * (stopX - startX) / numberXDivisions + startX;
                if(i % 10 == 0) {
                    if(drawXGrid) {
                        g2.setColor(gridColour);
                        g2.drawLine(x, stopY, x, startY);
                    }
                    if(drawXLabels) {
                        g2.setColor(labelColour);
                        String xLabel = ((int) ((minX + (maxX - minX) * ((i * 1.0) / numberXDivisions)) * 100)) / 100.0 + "";
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
            if(hasSecondYAxis) {
                g2.drawLine(stopX, startY, stopX, stopY);
            }
            g2.drawLine(startX, stopY, stopX, stopY);

            //draw x and y axes at 0
            if(minX < 0 && maxX > 0) {
                g2.drawLine(zeroX, startY, zeroX, stopY);
            }
            if(minYA < 0 && maxYA > 0) {
                g2.drawLine(startX, zeroYA, stopX, zeroYA);
            }
            if(hasSecondYAxis && (minYB < 0 && maxYB > 0)) {
                g2.drawLine(startX, zeroYB, stopX, zeroYB);
            }

            //render rows and functions
            //TODO actually render them
            g2.setStroke(graphStroke);
            for (GraphFunction graphFunction : graphFunctions) {
                g2.setColor(graphFunction.colour);
                for(int i = startX + 1; i < stopX; i++) {
                    int y0;
                    int y1;
                    if(!graphFunction.allocateToRightAxis) {
                        y0 = (int) (zeroYA + graphFunction.function(getXAt(i - 1)) * yAScale * -1);
                        y1 = (int) (zeroYA + graphFunction.function(getXAt(i)) * yAScale * -1);
                    } else {
                        y0 = (int) (zeroYB + graphFunction.function(getXAt(i - 1)) * yBScale * -1);
                        y1 = (int) (zeroYB + graphFunction.function(getXAt(i)) * yBScale * -1);
                    }
                    if(isYInGraphRange(y0) && isYInGraphRange(y1)) {
                        //g2.fillOval(i, y, pointWidth, pointWidth);
                        g2.drawLine(i - 1,y0, i, y1);
                    }
                }
            }

            //draw X and Y indication at Mouse-pointer
            if((indicateMouseX || indicateMouseY || labelMouseXY) && isInGraphRange(mouseX, mouseY)) {
                g2.setStroke(uiStroke);
                g2.setColor(indicatorColour);
                if(indicateMouseX) {
                    g2.drawLine(startX, mouseY, stopX, mouseY);
                }
                if(indicateMouseY) {
                    g2.drawLine(mouseX, startY, mouseX, stopY);
                }
                if(labelMouseXY) {
                    StringBuilder label = new StringBuilder()
                            .append(Math.round(getXAt(mouseX) * 100.0) / 100.0)
                            .append(", ")
                            .append(Math.round(getYAAt(mouseY) * 100.0) / 100.0);
                    if(hasSecondYAxis) {
                        label.append(", ").append(Math.round(getYBAt(mouseY) * 100.0) / 100.0);
                    }
                    g2.setColor(backgroundColour);
                    g2.fillRect(mouseX + 1, mouseY - metrics.getHeight() - 5, metrics.stringWidth(label.toString()) + 5, metrics.getHeight() + 5);
                    g2.setColor(indicatorColour);
                    g2.drawString(label.toString(), mouseX + 3, mouseY - 3);
                }
            }

            //cleanup
            g2.dispose();
            g.dispose();
        } catch (OutOfGraphBoundsException e) {
            e.printStackTrace();
        }
    }

    public boolean hasSecondYAxis() {
        return this.hasSecondYAxis;
    }

    private void updateSecondYAxis() {
        boolean rowOnRight = false;
        for (Row row : rows) {
            if (row.allocateToRightAxis) {
                rowOnRight = true;
                break;
            }
        }
        boolean functionOnRight = false;
        for (GraphFunction graphFunction : graphFunctions) {
            if (graphFunction.allocateToRightAxis) {
                functionOnRight = true;
                break;
            }
        }
        this.hasSecondYAxis = rowOnRight || functionOnRight;
    }

    public boolean isInGraphRange(int x, int y) {
        return isXInGraphRange(x) && isYInGraphRange(y);
    }

    public boolean isXInGraphRange(int x) {
        return x >= startX && x <= stopX;
    }

    public boolean isYInGraphRange(int y) {
        return y >= startY && y <= stopY;
    }

    public double getXAt(int x) throws OutOfGraphBoundsException {
        if(isXInGraphRange(x)) {
            return (x * 1.0 - startX) / xScale + minX;
        } else throw new OutOfGraphBoundsException("x not in Range");
    }

    public double getYAAt(int y) throws OutOfGraphBoundsException {
        if (isYInGraphRange(y)) {
            return ((y * 1.0 - stopY) / yAScale - minYA) * -1;
        } else throw new OutOfGraphBoundsException("yA not in Range");
    }

    public double getYBAt(int y) throws OutOfGraphBoundsException {
        if (isYInGraphRange(y)) {
            return ((y * 1.0 - stopY) / yBScale - minYB) * -1;
        } else throw new OutOfGraphBoundsException("yB not in Range");
    }

    public void addFunction(GraphFunction graphFunction) {
        graphFunctions.add(graphFunction);
        updateSecondYAxis();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setTitle(String title) {
        this.title = title;
        this.hasTitle = title != null;
        repaint();
    }

    public String getTitle() {
        return title;
    }

    public void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
        this.hasBackgroundImage = image != null;
        repaint();
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setPadding(int padding) {
        this.padding = padding;
        repaint();
    }

    public int getPadding() {
        return padding;
    }

    public void setLabelPadding(int labelPadding) {
        this.labelPadding = labelPadding;
        repaint();
    }

    public int getLabelPadding() {
        return labelPadding;
    }

    public void setTitlePadding(int titlePadding) {
        this.titlePadding = titlePadding;
        repaint();
    }

    public int getTitlePadding() {
        return titlePadding;
    }

    public void setPointWidth(int pointWidth) {
        this.pointWidth = pointWidth;
        repaint();
    }

    public int getPointWidth() {
        return pointWidth;
    }

    public void setNumberXDivisions(int numberXDivisions) {
        this.numberXDivisions = numberXDivisions;
        repaint();
    }

    public int getNumberXDivisions() {
        return numberXDivisions;
    }

    public void setNumberYDivisions(int numberYDivisions) {
        this.numberYDivisions = numberYDivisions;
        repaint();
    }

    public int getNumberYDivisions() {
        return numberYDivisions;
    }

    public void setDrawXGrid(boolean drawXGrid) {
        this.drawXGrid = drawXGrid;
        repaint();
    }

    public boolean isDrawXGrid() {
        return drawXGrid;
    }

    public void setDrawYGrid(boolean drawYGrid) {
        this.drawYGrid = drawYGrid;
        repaint();
    }

    public boolean isDrawYGrid() {
        return drawYGrid;
    }

    public void setDrawXHatchMarks(boolean drawXHatchMarks) {
        this.drawXHatchMarks = drawXHatchMarks;
        repaint();
    }

    public boolean isDrawXHatchMarks() {
        return drawXHatchMarks;
    }

    public void setDrawYAHatchMarks(boolean drawYAHatchMarks) {
        this.drawYAHatchMarks = drawYAHatchMarks;
        repaint();
    }

    public boolean isDrawYAHatchMarks() {
        return drawYAHatchMarks;
    }

    public void setDrawYBHatchMarks(boolean drawYBHatchMarks) {
        this.drawYBHatchMarks = drawYBHatchMarks;
        repaint();
    }

    public boolean isDrawYBHatchMarks() {
        return drawYBHatchMarks;
    }

    public void setDrawXLabels(boolean drawXLabels) {
        this.drawXLabels = drawXLabels;
        repaint();
    }

    public boolean isDrawXLabels() {
        return drawXLabels;
    }

    public void setDrawYALabels(boolean drawYALabels) {
        this.drawYALabels = drawYALabels;
        repaint();
    }

    public boolean isDrawYALabels() {
        return drawYALabels;
    }

    public void setDrawYBLabels(boolean drawYBLabels) {
        this.drawYBLabels = drawYBLabels;
        repaint();
    }

    public boolean isDrawYBLabels() {
        return drawYBLabels;
    }

    public void setIndicateMouseX(boolean indicateMouseX) {
        this.indicateMouseX = indicateMouseX;
        repaint();
    }

    public boolean isIndicateMouseX() {
        return indicateMouseX;
    }

    public void setIndicateMouseY(boolean indicateMouseY) {
        this.indicateMouseY = indicateMouseY;
        repaint();
    }

    public boolean isIndicateMouseY() {
        return indicateMouseY;
    }

    public void setLabelMouseXY(boolean labelMouseXY) {
        this.labelMouseXY = labelMouseXY;
        repaint();
    }

    public boolean isLabelMouseXY() {
        return labelMouseXY;
    }

    public void setBackgroundColour(Color backgroundColour) {
        this.backgroundColour = backgroundColour;
        repaint();
    }

    public Color getBackgroundColour() {
        return backgroundColour;
    }

    public void setGridColour(Color gridColour) {
        this.gridColour = gridColour;
        repaint();
    }

    public Color getGridColour() {
        return gridColour;
    }

    public void setLabelColour(Color labelColour) {
        this.labelColour = labelColour;
        repaint();
    }

    public Color getLabelColour() {
        return labelColour;
    }

    public void setTitleColour(Color titleColour) {
        this.titleColour = titleColour;
        repaint();
    }

    public Color getTitleColour() {
        return titleColour;
    }

    public void setAxisColour(Color axisColour) {
        this.axisColour = axisColour;
        repaint();
    }

    public Color getAxisColour() {
        return axisColour;
    }

    public void setHatchMarkColour(Color hatchMarkColour) {
        this.hatchMarkColour = hatchMarkColour;
        repaint();
    }

    public Color getHatchMarkColour() {
        return hatchMarkColour;
    }

    public void setIndicatorColour(Color indicatorColour) {
        this.indicatorColour = indicatorColour;
        repaint();
    }

    public Color getIndicatorColour() {
        return indicatorColour;
    }

    public void setGraphStroke(Stroke graphStroke) {
        this.graphStroke = graphStroke;
        repaint();
    }

    public Stroke getGraphStroke() {
        return graphStroke;
    }

    public void setUiStroke(Stroke uiStroke) {
        this.uiStroke = uiStroke;
        repaint();
    }

    public Stroke getUiStroke() {
        return uiStroke;
    }

    public void setMinX(double minX) {
        this.minX = minX;
        repaint();
    }

    public double getMinX() {
        return minX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
        repaint();
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMinYA(double minYA) {
        this.minYA = minYA;
        repaint();
    }

    public double getMinYA() {
        return minYA;
    }

    public void setMinYB(double minYB) {
        this.minYB = minYB;
        repaint();
    }

    public double getMinYB() {
        return minYB;
    }

    public void setMaxYA(double maxYA) {
        this.maxYA = maxYA;
        repaint();
    }

    public double getMaxYA() {
        return maxYA;
    }

    public void setMaxYB(double maxYB) {
        this.maxYB = maxYB;
        repaint();
    }

    public double getMaxYB() {
        return maxYB;
    }
}
