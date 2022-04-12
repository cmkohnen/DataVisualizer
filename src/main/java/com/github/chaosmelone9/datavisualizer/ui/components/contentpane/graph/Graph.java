package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import com.github.chaosmelone9.datavisualizer.config.GraphConfig;

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

    List<GraphFunction> graphFunctions = new ArrayList<>();
    List<GraphMarker> graphMarkers = new ArrayList<>();
    List<GraphOval> graphOvals = new ArrayList<>();
    List<GraphPoint> graphPoints = new ArrayList<>();
    List<GraphPolygon> graphPolygons = new ArrayList<>();
    List<GraphRow> graphRows = new ArrayList<>();

    private String title = GraphConfig.DEFAULT_TITLE;

    private int padding = GraphConfig.DEFAULT_PADDING;
    private int labelPadding = GraphConfig.DEFAULT_LABEL_PADDING;
    private int titlePadding = GraphConfig.DEFAULT_TITLE_PADDING;

    private int pointWidth = GraphConfig.DEFAULT_POINT_WIDTH;
    private int numberXDivisions = GraphConfig.DEFAULT_NUMBER_X_DIVISIONS;
    private int numberYDivisions = GraphConfig.DEFAULT_NUMBER_Y_DIVISIONS;

    private boolean drawXGrid = GraphConfig.DEFAULT_DRAW_X_GRID;
    private boolean drawYGrid = GraphConfig.DEFAULT_DRAW_Y_GRID;
    private boolean drawXHatchMarks = GraphConfig.DEFAULT_DRAW_X_HATCH_MARKS;
    private boolean drawYAHatchMarks = GraphConfig.DEFAULT_DRAW_YA_HATCH_MARKS;
    private boolean drawYBHatchMarks = GraphConfig.DEFAULT_DRAW_YB_HATCH_MARKS;
    private boolean drawXLabels = GraphConfig.DEFAULT_DRAW_X_LABELS;
    private boolean drawYALabels = GraphConfig.DEFAULT_DRAW_YA_LABELS;
    private boolean drawYBLabels = GraphConfig.DEFAULT_DRAW_YB_LABELS;
    private boolean indicateMouseX = GraphConfig.DEFAULT_INDICATE_MOUSE_X;
    private boolean indicateMouseY = GraphConfig.DEFAULT_INDICATE_MOUSE_Y;
    private boolean labelMouseXY = GraphConfig.DEFAULT_LABEL_MOUSE_XY;

    private Color backgroundColour = GraphConfig.DEFAULT_BACKGROUND_COLOUR;
    private Color gridColour = GraphConfig.DEFAULT_GRID_COLOUR;
    private Color labelColour = GraphConfig.DEFAULT_LABEL_COLOUR;
    private Color titleColour = GraphConfig.DEFAULT_TITLE_COLOUR;
    private Color axisColour = GraphConfig.DEFAULT_AXIS_COLOUR;
    private Color hatchMarkColour = GraphConfig.DEFAULT_HATCH_MARK_COLOUR;
    private Color indicatorColour = GraphConfig.DEFAULT_INDICATOR_COLOUR;

    private BufferedImage backgroundImage = GraphConfig.DEFAULT_BACKGROUND_IMAGE;

    private Stroke graphStroke = GraphConfig.DEFAULT_GRAPH_STROKE;
    private Stroke uiStroke = GraphConfig.DEFAULT_UI_STROKE;

    private double minX = GraphConfig.DEFAULT_MIN_X;
    private double maxX = GraphConfig.DEFAULT_MAX_X;
    private double minYA = GraphConfig.DEFAULT_MIN_YA;
    private double minYB = GraphConfig.DEFAULT_MIN_YB;
    private double maxYA = GraphConfig.DEFAULT_MAY_YA;
    private double maxYB = GraphConfig.DEFAULT_MAX_YB;

    private boolean hasSecondYAxis = false;
    private boolean hasTitle = title != null;
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

    private int zeroX;
    private int zeroYA;
    private int zeroYB;

    public Graph() {
        super();
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
            /* begin setup of rendering and adjust values*/
            //setup rendering
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
            this.zeroX = (int) Math.abs(minX * xScale) + startX;
            this.zeroYA = (int) (stopY - Math.abs(minYA * yAScale));
            this.zeroYB = (int) (stopY - Math.abs(minYB * yBScale));

            /*begin rendering of ui*/
            g2.setStroke(uiStroke);
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

            /* Begin rendering of Content */
            g2.setStroke(graphStroke);
            //render rows
            for (GraphRow graphRow : graphRows) {
                g2.setColor(graphRow.colour);
                for (int i = 0; i < graphRow.row.points.length - 1; i++) {
                    int x0 = getXOf(graphRow.row.points[i].x);
                    int x1 = getXOf(graphRow.row.points[i + 1].x);
                    int y0 = getYOf(graphRow.row.points[i].y, graphRow.allocateToRightAxis);
                    int y1 = getYOf(graphRow.row.points[i +1].y, graphRow.allocateToRightAxis);
                    if(isInGraphRange(x0, y0) && isInGraphRange(x1, y1)) {
                        g2.drawLine(x0, y0, x1, y1);
                    }
                }
            }

            //render functions
            for (GraphFunction graphFunction : graphFunctions) {
                g2.setColor(graphFunction.colour);
                for(int i = startX + 1; i < stopX; i++) {
                    int y0 = getYOf(graphFunction.function.apply(getXAt(i - 1)), graphFunction.allocateToRightAxis);
                    int y1 = getYOf(graphFunction.function.apply(getXAt(i)), graphFunction.allocateToRightAxis);
                    if(isYInGraphRange(y0) && isYInGraphRange(y1)) {
                        g2.drawLine(i - 1,y0, i, y1);
                    }
                }
            }

            //render ovals
            for (GraphOval graphOval : graphOvals) {
                g2.setColor(graphOval.colour);
                int x = getXOf(graphOval.oval.center.x);
                int y = getYOf(graphOval.oval.center.y, graphOval.allocateToRightAxis);
                int x1 = (int) (graphOval.oval.xHeight * xScale);
                int y1;
                if(graphOval.allocateToRightAxis) {
                    y1 = (int) (graphOval.oval.yHeight * yBScale);
                } else {
                    y1 = (int) (graphOval.oval.yHeight * yAScale);
                }
                if(graphOval.filled) {
                    g2.fillOval(x, y, x1, y1);
                } else {
                    g2.drawOval(x, y, x1, y1);
                }
            }

            //render polygons
            for (GraphPolygon graphPolygon : graphPolygons) {
                g2.setColor(graphPolygon.colour);
                Polygon polygon = new Polygon();
                for (com.github.chaosmelone9.datavisualizer.datasets.Point point : graphPolygon.polygon.points) {
                    int x = getXOf(point.x);
                    int y = getYOf(point.y, graphPolygon.allocateToRightAxis);
                    if(x > stopX) {
                        x = stopX;
                    } else if(x < startX) {
                        x = startX;
                    }
                    if(y > stopY) {
                        y = stopY;
                    } else if(y < startY) {
                        y = startY;
                    }
                    polygon.addPoint(x, y);
                }
                if(graphPolygon.filled) {
                    g2.fillPolygon(polygon);
                } else {
                    g2.drawPolygon(polygon);
                }
            }

            //render points
            for (GraphPoint graphPoint : graphPoints) {
                g2.setColor(graphPoint.color);
                int x = getXOf(graphPoint.point.x);
                int y = getYOf(graphPoint.point.y, graphPoint.allocateToRightAxis);
                if(isInGraphRange(x, y)) {
                    g2.fillOval(x, y, pointWidth, pointWidth);
                }
            }

            //render Markers
            for (GraphMarker graphMarker : graphMarkers) {
                g2.setColor(graphMarker.colour);
                if(graphMarker.xOrY) {
                    int x = getXOf(graphMarker.value);
                    g2.drawLine(x, startY, x, stopY);
                } else {
                    int y = getYOf(graphMarker.value, graphMarker.allocateToRightAxis);
                    g2.drawLine(startX, y, stopX, y);
                }
            }


            /* resume rendering of ui*/
            g2.setStroke(uiStroke);
            // create hatch marks and grid lines for y-axes.
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

            // create hatch marks and grid lines for x-axis.
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

            /*cleanup*/
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
        boolean functionOnRight = false;
        for (GraphFunction graphFunction : graphFunctions) {
            if (graphFunction.allocateToRightAxis) {
                functionOnRight = true;
                break;
            }
        }
        boolean markerOnRight = false;
        for (GraphMarker graphMarker : graphMarkers) {
            if(graphMarker.allocateToRightAxis) {
                markerOnRight = true;
                break;
            }
        }
        boolean ovalOnRight = false;
        for (GraphOval graphOval : graphOvals) {
            if(graphOval.allocateToRightAxis) {
                ovalOnRight = true;
                break;
            }
        }
        boolean pointOnRight = false;
        for (GraphPoint graphPoint : graphPoints) {
            if(graphPoint.allocateToRightAxis) {
                pointOnRight = true;
                break;
            }
        }
        boolean polygonOnRight = false;
        for (GraphPolygon graphPolygon : graphPolygons) {
            if(graphPolygon.allocateToRightAxis) {
                polygonOnRight = true;
                break;
            }
        }
        boolean rowOnRight = false;
        for (GraphRow graphRow : graphRows) {
            if (graphRow.allocateToRightAxis) {
                rowOnRight = true;
                break;
            }
        }
        this.hasSecondYAxis = functionOnRight || markerOnRight || ovalOnRight || pointOnRight || polygonOnRight || rowOnRight;
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

    private int getXOf(double x) {
        return (int) (zeroX + x * xScale);
    }

    private int getYAOf(double y) {
        return (int) (zeroYA - y * yAScale);
    }

    private int getYBOf(double y) {
        return (int) (zeroYB - y * yBScale);
    }

    private int getYOf(double y, boolean isOnRightAxis) {
        if(isOnRightAxis) {
            return getYBOf(y);
        } else {
            return getYAOf(y);
        }
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
        repaint();
    }

    public void addMarker(GraphMarker graphMarker) {
        graphMarkers.add(graphMarker);
        updateSecondYAxis();
        repaint();
    }

    public void addOval(GraphOval graphOval) {
        graphOvals.add(graphOval);
        updateSecondYAxis();
        repaint();
    }

    public void addPoint(GraphPoint graphPoint) {
        graphPoints.add(graphPoint);
        updateSecondYAxis();
        repaint();
    }

    public void addPolygon(GraphPolygon graphPolygon) {
        graphPolygons.add(graphPolygon);
        updateSecondYAxis();
        repaint();
    }

    public void addRow(GraphRow graphRow) {
        graphRows.add(graphRow);
        updateSecondYAxis();
        repaint();
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
