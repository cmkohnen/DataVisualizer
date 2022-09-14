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
package com.github.chaosmelone9.datavisualizer.ui.components.contentpane.graph;

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.config.GraphConfig;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author "Christoph Kohnen", "Hovercraft Full of Eels", "Rodrigo Azevedo"
 * <p>
 * The Base of this Component is an improved version of Hovercraft Full of Eels (<a href="https://stackoverflow.com/users/522444/hovercraft-full-of-eels">...</a>)
 * answer on StackOverflow: <a href="https://stackoverflow.com/a/8693635/753012">...</a> by Rodrigo Azevedo. Huge credits to them for figuring out the maths behind this.
 * However, this is heavily modified to include e.g. multiple rows, mathematical functions two y-axes, etc.
 */
public class Graph extends JPanel {

    private final Main instance;

    List<GraphFunction> graphFunctions = new ArrayList<>();
    List<GraphMarker> graphMarkers = new ArrayList<>();
    List<GraphOval> graphOvals = new ArrayList<>();
    List<GraphPoint> graphPoints = new ArrayList<>();
    List<GraphPolygon> graphPolygons = new ArrayList<>();
    List<GraphRow> graphRows = new ArrayList<>();

    private String title = GraphConfig.DEFAULT_TITLE;

    private Dimension minimumSize = GraphConfig.DEFAULT_MINIMUM_SIZE;
    private int padding = GraphConfig.DEFAULT_PADDING;
    private int labelPadding = GraphConfig.DEFAULT_LABEL_PADDING;
    private int titlePadding = GraphConfig.DEFAULT_TITLE_PADDING;

    private int pointWidth = GraphConfig.DEFAULT_POINT_WIDTH;
    private int numberXDivisions = GraphConfig.DEFAULT_NUMBER_X_DIVISIONS;
    private int numberYDivisions = GraphConfig.DEFAULT_NUMBER_Y_DIVISIONS;

    private boolean drawXGrid = GraphConfig.DEFAULT_DRAW_X_GRID;
    private boolean drawYGrid = GraphConfig.DEFAULT_DRAW_Y_GRID;
    private boolean drawXAHatchMarks = GraphConfig.DEFAULT_DRAW_XA_HATCH_MARKS;
    private boolean drawXBHatchMarks = GraphConfig.DEFAULT_DRAW_XB_HATCH_MARKS;
    private boolean drawYAHatchMarks = GraphConfig.DEFAULT_DRAW_YA_HATCH_MARKS;
    private boolean drawYBHatchMarks = GraphConfig.DEFAULT_DRAW_YB_HATCH_MARKS;
    private boolean drawXALabels = GraphConfig.DEFAULT_DRAW_XA_LABELS;
    private boolean drawXBLabels = GraphConfig.DEFAULT_DRAW_XB_LABELS;
    private boolean drawYALabels = GraphConfig.DEFAULT_DRAW_YA_LABELS;
    private boolean drawYBLabels = GraphConfig.DEFAULT_DRAW_YB_LABELS;
    private boolean indicateMouseX = GraphConfig.DEFAULT_INDICATE_MOUSE_X;
    private boolean indicateMouseY = GraphConfig.DEFAULT_INDICATE_MOUSE_Y;
    private boolean labelMouseXY = GraphConfig.DEFAULT_LABEL_MOUSE_XY;

    private Color backgroundColour = GraphConfig.DEFAULT_BACKGROUND_COLOUR;
    private Color gridColour = GraphConfig.DEFAULT_GRID_COLOUR;
    private Color labelColour = GraphConfig.DEFAULT_LABEL_COLOUR;
    private Color labelSecondColour = GraphConfig.DEFAULT_SECOND_LABEL_COLOUR;
    private Color titleColour = GraphConfig.DEFAULT_TITLE_COLOUR;
    private Color axisColour = GraphConfig.DEFAULT_AXIS_COLOUR;
    private Color hatchMarkColour = GraphConfig.DEFAULT_HATCH_MARK_COLOUR;
    private Color indicatorColour = GraphConfig.DEFAULT_INDICATOR_COLOUR;

    private Color uiColour = GraphConfig.DEFAULT_UI_COLOUR;

    private BufferedImage backgroundImage = GraphConfig.DEFAULT_BACKGROUND_IMAGE;

    private Stroke graphStroke = GraphConfig.DEFAULT_GRAPH_STROKE;
    private Stroke uiStroke = GraphConfig.DEFAULT_UI_STROKE;

    private double minXA = GraphConfig.DEFAULT_MIN_XA;
    private double minXB = GraphConfig.DEFAULT_MIN_XB;
    private double maxXA = GraphConfig.DEFAULT_MAX_XA;
    private double maxXB = GraphConfig.DEFAULT_MAX_XB;
    private double minYA = GraphConfig.DEFAULT_MIN_YA;
    private double minYB = GraphConfig.DEFAULT_MIN_YB;
    private double maxYA = GraphConfig.DEFAULT_MAY_YA;
    private double maxYB = GraphConfig.DEFAULT_MAX_YB;

    private boolean hasSecondYAxis = false;
    private boolean hasSecondXAxis = false;
    private boolean hasTitle = false;
    private boolean hasBackgroundImage = false;

    private int mouseX;
    private int mouseY;

    private int startX;
    private int stopX;
    private int startY;
    private int stopY;

    private double xAScale;
    private double xBScale;
    private double yAScale;
    private double yBScale;

    private int zeroXA;
    private int zeroXB;
    private int zeroYA;
    private int zeroYB;

    public Graph(Main instance) {
        super();
        this.instance = instance;
        GraphListener listener = new GraphListener();
        addMouseMotionListener(listener);
        addMouseListener(listener);
        addMouseWheelListener(listener);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        try {
            final int width = getWidth();
            final int height = getHeight();
            //setup rendering
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            GraphRenderHelper renderHelper = new GraphRenderHelper(graphics2D);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            FontMetrics metrics = graphics2D.getFontMetrics();
            //figure out whether sufficient space for the graph is present
            if(!(width > minimumSize.width && height > minimumSize.height)) {
                String notEnoughScreenSpace = "Not enough space to display graph.";
                BufferedImage image = ImageIO.read(instance.getFetcher().fetch("warning.png"));
                graphics2D.drawImage(image, (width / 2) - 25, (height / 2) - 35, 50, 50, this);
                graphics2D.setColor(labelColour);
                graphics2D.drawString(notEnoughScreenSpace, (width / 2) - (metrics.stringWidth(notEnoughScreenSpace) / 2), (height / 2) + (metrics.getHeight() / 2) + 20);
            } else {
                //figuring out dimensions of the graph
                this.startX = padding + labelPadding;
                this.startY = padding;
                if(hasSecondXAxis) {
                    this.startY += labelPadding;
                }
                if(hasTitle) {
                    this.startY += titlePadding;
                }
                this.stopX = width - padding;
                this.stopY = height - padding - labelPadding;
                if(hasSecondYAxis) {
                    this.stopX -= labelPadding;
                }
                this.xAScale = (stopX - startX) / (maxXA - minXA);
                this.xBScale = (stopX - startX) / (maxXB - minXB);
                this.yAScale = (stopY - startY) / (maxYA - minYA);
                this.yBScale = (stopY - startY) / (maxYB - minYB);

                //figure out where 0s are
                this.zeroXA = (int) Math.abs(minXA * xAScale) + startX;
                this.zeroXB = (int) Math.abs(minXB * xBScale) + startX;
                this.zeroYA = (int) (stopY - Math.abs(minYA * yAScale));
                this.zeroYB = (int) (stopY - Math.abs(minYB * yBScale));

                /*begin rendering of ui*/
                graphics2D.setStroke(uiStroke);
                //draw Title
                if(hasTitle) {
                    graphics2D.setColor(titleColour);
                    graphics2D.drawString(title,(width / 2) - (metrics.stringWidth(title) / 2), padding);
                }

                //paint background
                graphics2D.setColor(backgroundColour);
                int backgroundWidth = stopX - startX;
                int backgroundHeight = stopY - startY;
                graphics2D.fillRect(startX, startY, backgroundWidth, backgroundHeight);
                if(hasBackgroundImage) {
                    graphics2D.drawImage(backgroundImage, startX, startY, backgroundWidth, backgroundHeight, this);
                }

                /* Begin rendering of Content */
                graphics2D.setStroke(graphStroke);
                //render rows
                for (GraphRow graphRow : graphRows) {
                    graphics2D.setColor(graphRow.colour);
                    for (int i = 0; i < graphRow.row.points.length - 1; i++) {
                        int x0 = getXOf(graphRow.row.points[i].x, graphRow.allocateToSecondXAxis);
                        int x1 = getXOf(graphRow.row.points[i + 1].x, graphRow.allocateToSecondXAxis);
                        int y0 = getYOf(graphRow.row.points[i].y, graphRow.allocateToSecondYAxis);
                        int y1 = getYOf(graphRow.row.points[i +1].y, graphRow.allocateToSecondYAxis);
                        if(isInGraphRange(x0, y0) && isInGraphRange(x1, y1)) {
                            graphics2D.drawLine(x0, y0, x1, y1);
                        }
                    }
                }

                //render functions
                for (GraphFunction graphFunction : graphFunctions) {
                    graphics2D.setColor(graphFunction.colour);
                    for(int i = startX + 1; i < stopX; i++) {
                        int y0 = getYOf(graphFunction.function.apply(getXAt(i - 1, graphFunction.allocateToSecondXAxis)), graphFunction.allocateToSecondYAxis);
                        int y1 = getYOf(graphFunction.function.apply(getXAt(i, graphFunction.allocateToSecondXAxis)), graphFunction.allocateToSecondYAxis);
                        if(isYInGraphRange(y0) && isYInGraphRange(y1)) {
                            graphics2D.drawLine(i - 1,y0, i, y1);
                        }
                    }
                }

                //render ovals
                for (GraphOval graphOval : graphOvals) {
                    graphics2D.setColor(graphOval.colour);
                    int x = getXOf(graphOval.oval.center.x, graphOval.allocateToSecondXAxis);
                    int y = getYOf(graphOval.oval.center.y, graphOval.allocateToSecondYAxis);
                    int x1;
                    int y1;
                    if(graphOval.allocateToSecondXAxis) {
                        x1 = (int) (graphOval.oval.xHeight * xBScale);
                    } else {
                        x1 = (int) (graphOval.oval.xHeight * xAScale);
                    }
                    if(graphOval.allocateToSecondYAxis) {
                        y1 = (int) (graphOval.oval.yHeight * yBScale);
                    } else {
                        y1 = (int) (graphOval.oval.yHeight * yAScale);
                    }
                    if(graphOval.filled) {
                        graphics2D.fillOval(x, y, x1, y1);
                    } else {
                        graphics2D.drawOval(x, y, x1, y1);
                    }
                }

                //render polygons
                for (GraphPolygon graphPolygon : graphPolygons) {
                    graphics2D.setColor(graphPolygon.colour);
                    Polygon polygon = new Polygon();
                    for (com.github.chaosmelone9.datavisualizer.datasets.Point point : graphPolygon.polygon.points) {
                        int x = getXOf(point.x, graphPolygon.allocateToSecondXAxis);
                        int y = getYOf(point.y, graphPolygon.allocateToSecondYAxis);
                        //TODO do some fancy math to make this work
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
                        graphics2D.fillPolygon(polygon);
                    } else {
                        graphics2D.drawPolygon(polygon);
                    }
                }

                //render points
                for (GraphPoint graphPoint : graphPoints) {
                    graphics2D.setColor(graphPoint.colour);
                    int x = getXOf(graphPoint.point.x, graphPoint.allocateToSecondXAxis);
                    int y = getYOf(graphPoint.point.y, graphPoint.allocateToSecondYAxis);
                    if(isInGraphRange(x, y)) {
                        graphics2D.fillOval(x, y, pointWidth, pointWidth);
                    }
                }

                //render Markers
                for (GraphMarker graphMarker : graphMarkers) {
                    graphics2D.setColor(graphMarker.colour);
                    if(graphMarker.xOrY) {
                        int x = getXOf(graphMarker.value, graphMarker.allocateToSecondXAxis);
                        graphics2D.drawLine(x, startY, x, stopY);
                    } else {
                        int y = getYOf(graphMarker.value, graphMarker.allocateToSecondYAxis);
                        graphics2D.drawLine(startX, y, stopX, y);
                    }
                }


                /* resume rendering of ui*/
                graphics2D.setStroke(uiStroke);
                // create hatch marks and grid lines for y-axes.
                for (int i = 0; i < numberYDivisions + 1; i++) {
                    int y = stopY - ((i * (stopY - startY)) / numberYDivisions);
                    if(i % 10 == 0) {
                        if(drawYGrid) {
                            graphics2D.setColor(gridColour);
                            graphics2D.drawLine(startX, y, stopX, y);
                        }
                        if(drawYALabels || drawYBLabels) {
                            graphics2D.setColor(labelColour);
                            if(drawYALabels) {
                                String yALabel = ((int) ((minYA + (maxYA - minYA) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                                graphics2D.drawString(yALabel, startX - metrics.stringWidth(yALabel) - 5, y + (metrics.getHeight() / 2) - 3);
                            }
                            if(hasSecondYAxis && drawYBLabels) {
                                String yBLabel = ((int) ((minYB + (maxYB - minYB) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                                graphics2D.drawString(yBLabel, stopX + 5, y + (metrics.getHeight() / 2) - 3);
                            }
                        }
                    } else if(drawYAHatchMarks || drawYBHatchMarks) {
                        graphics2D.setColor(hatchMarkColour);
                        if(drawYAHatchMarks) {
                            graphics2D.drawLine(startX, y, startX + pointWidth, y);
                        }
                        if(hasSecondYAxis && drawYBHatchMarks) {
                            graphics2D.drawLine(stopX, y, stopX - pointWidth, y);
                        }
                    }
                }

                // create hatch marks and grid lines for x-axis.
                for (int i = 0; i < numberXDivisions + 1; i++) {
                    int x = i * (stopX - startX) / numberXDivisions + startX;
                    if(i % 10 == 0) {
                        if(drawXGrid) {
                            graphics2D.setColor(gridColour);
                            graphics2D.drawLine(x, stopY, x, startY);
                        }
                        if(drawXALabels || drawXBLabels) {
                            graphics2D.setColor(labelColour);
                            if(drawXALabels) {
                                String xALabel = ((int) ((minXA + (maxXA - minXA) * ((i * 1.0) / numberXDivisions)) * 100)) / 100.0 + "";
                                graphics2D.drawString(xALabel, x - metrics.stringWidth(xALabel) / 2, stopY + metrics.getHeight() - 3);
                            }
                            if(hasSecondXAxis && drawXBLabels) {
                                String xBLabel = ((int) ((minXB + (maxXB - minXB) * ((i * 1.0) / numberXDivisions)) * 100)) / 100.0 + "";
                                graphics2D.drawString(xBLabel, x - metrics.stringWidth(xBLabel) / 2, startY - 3);
                            }
                        }
                    } else if(drawXAHatchMarks || drawXBHatchMarks) {
                        graphics2D.setColor(hatchMarkColour);
                        if(drawXAHatchMarks) {
                            graphics2D.drawLine(x, stopY, x, stopY - pointWidth);
                        }
                        if(hasSecondXAxis && drawXBHatchMarks) {
                            graphics2D.drawLine(x, startY, x, startY + pointWidth);
                        }
                    }
                }

                // create x and y axes
                graphics2D.setColor(axisColour);
                graphics2D.drawLine(startX, startY, startX, stopY);
                if(hasSecondYAxis) {
                    graphics2D.drawLine(stopX, startY, stopX, stopY);
                }
                graphics2D.drawLine(startX, stopY, stopX, stopY);
                if(hasSecondXAxis) {
                    graphics2D.drawLine(startX, startY, stopX, startY);
                }

                //draw x and y axes at 0 and label them
                if(minXA < 0 && maxXA > 0) {
                    graphics2D.setColor(axisColour);
                    graphics2D.drawLine(zeroXA, startY, zeroXA, stopY);
                    Rectangle labelRectangle = new Rectangle(zeroXA - (metrics.stringWidth("0") / 2) - 2, stopY, metrics.stringWidth("0") + 4, metrics.getHeight());
                    renderHelper.drawBorderedRect(labelRectangle, backgroundColour, uiColour);
                    graphics2D.setColor(labelSecondColour);
                    graphics2D.drawString("0",zeroXA - (metrics.stringWidth("0") / 2) ,stopY + metrics.getHeight() - 3 );
                }
                if(hasSecondXAxis && minXB < 0 && maxXB > 0) {
                    graphics2D.setColor(axisColour);
                    graphics2D.drawLine(zeroXB, startY, zeroXB, stopY);
                    Rectangle labelRectangle = new Rectangle(zeroXB - (metrics.stringWidth("0") / 2) - 2, startY - metrics.getHeight(), metrics.stringWidth("0") + 4, metrics.getHeight());
                    renderHelper.drawBorderedRect(labelRectangle, backgroundColour, uiColour);
                    graphics2D.setColor(labelSecondColour);
                    graphics2D.drawString("0",zeroXB - (metrics.stringWidth("0") / 2) ,startY - 3 );
                }
                if(minYA < 0 && maxYA > 0) {
                    graphics2D.setColor(axisColour);
                    graphics2D.drawLine(startX, zeroYA, stopX, zeroYA);
                    Rectangle labelRectangle = new Rectangle(startY - metrics.stringWidth("0") - 8, zeroYA - (metrics.getHeight() / 2), metrics.stringWidth("0") + 8, metrics.getHeight());
                    renderHelper.drawBorderedRect(labelRectangle, backgroundColour, uiColour);
                    graphics2D.setColor(labelSecondColour);
                    graphics2D.drawString("0", startX - metrics.stringWidth("0") - 5, zeroYA + (metrics.getHeight() / 2) - 3);

                }
                if(hasSecondYAxis && (minYB < 0 && maxYB > 0)) {
                    graphics2D.setColor(axisColour);
                    graphics2D.drawLine(startX, zeroYB, stopX, zeroYB);
                    Rectangle labelRectangle = new Rectangle(stopX, zeroYB - (metrics.getHeight() / 2), metrics.stringWidth("0") + 8, metrics.getHeight());
                    renderHelper.drawBorderedRect(labelRectangle, backgroundColour, uiColour);
                    graphics2D.setColor(labelSecondColour);
                    graphics2D.drawString("0", stopX + 5, zeroYB + (metrics.getHeight() / 2) - 3);
                }

                //draw X and Y indication at Mouse-pointer
                if((indicateMouseX || indicateMouseY || labelMouseXY) && isInGraphRange(mouseX, mouseY)) {
                    graphics2D.setStroke(uiStroke);
                    graphics2D.setColor(indicatorColour);
                    if(indicateMouseX) {
                        graphics2D.drawLine(startX, mouseY, stopX, mouseY);
                    }
                    if(indicateMouseY) {
                        graphics2D.drawLine(mouseX, startY, mouseX, stopY);
                    }
                    if(labelMouseXY) {
                        StringBuilder label = new StringBuilder().append(Math.round(getXAAt(mouseX) * 100.0) / 100.0);
                        if(hasSecondXAxis) {
                            label.append("/").append(Math.round(getXBAt(mouseX) * 100.0) / 100.0);
                        }
                        label.append(", ").append(Math.round(getYAAt(mouseY) * 100.0) / 100.0);
                        if(hasSecondYAxis) {
                            label.append("/").append(Math.round(getYBAt(mouseY) * 100.0) / 100.0);
                        }
                        int labelX;
                        int labelWidth = metrics.stringWidth(label.toString());
                        if(mouseX > (width / 2)) {
                            labelX = mouseX - labelWidth;
                        } else {
                            labelX = mouseX;
                        }
                        Rectangle rectangle = new Rectangle(labelX + 1, mouseY - metrics.getHeight() - 5,  labelWidth + 5, metrics.getHeight() + 5);
                        renderHelper.drawBorderedRect(rectangle, backgroundColour, uiColour);
                        graphics2D.setColor(labelColour);
                        graphics2D.drawString(label.toString(), labelX + 3, mouseY - 3);
                    }
                }

                /*cleanup*/
                graphics2D.dispose();
                graphics.dispose();
            }
        } catch (OutOfGraphBoundsException | IOException e) {
            instance.getLogger().logStackTrace(e);
        }
    }

    public boolean hasSecondYAxis() {
        return this.hasSecondYAxis;
    }

    public boolean hasSecondXAxis() {
        return this.hasSecondXAxis;
    }

    private void updateSecondAxes() {
        boolean secondX = false;
        boolean secondY = false;
        List<GraphObject> objects = new ArrayList<>();
        objects.addAll(graphFunctions);
        objects.addAll(graphMarkers);
        objects.addAll(graphOvals);
        objects.addAll(graphPoints);
        objects.addAll(graphPolygons);
        objects.addAll(graphRows);
        for (GraphObject object : objects) {
            if(object.allocateToSecondXAxis) {
                secondX = true;
            }
            if(object.allocateToSecondYAxis) {
                secondY = true;
            }
        }
        this.hasSecondXAxis = secondX;
        this.hasSecondYAxis = secondY;
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

    private int getXAOf(double x) {
        return (int) (zeroXA + x * xAScale);
    }
    private int getXBOf(double x) {
        return (int) (zeroXB + x * xBScale);
    }

    private int getXOf(double x, boolean allocateToSecondXAxis) {
        if(allocateToSecondXAxis) {
            return getXBOf(x);
        } else {
            return getXAOf(x);
        }
    }

    private int getYAOf(double y) {
        return (int) (zeroYA - y * yAScale);
    }

    private int getYBOf(double y) {
        return (int) (zeroYB - y * yBScale);
    }

    private int getYOf(double y, boolean allocateToSecondYAxis) {
        if(allocateToSecondYAxis) {
            return getYBOf(y);
        } else {
            return getYAOf(y);
        }
    }

    public double getXAAt(int x) throws OutOfGraphBoundsException {
        if(isXInGraphRange(x)) {
            return (x * 1.0 - startX) / xAScale + minXA;
        } else throw new OutOfGraphBoundsException("xA");
    }

    public double getXBAt(int x) throws OutOfGraphBoundsException {
        if(isXInGraphRange(x)) {
            return (x * 1.0 - startX) / xBScale + minXB;
        } else throw new OutOfGraphBoundsException("xB");
    }

    public double getXAt(int x, boolean allocateToSecondXAxis) throws OutOfGraphBoundsException {
        if(allocateToSecondXAxis) {
            return getXBAt(x);
        } else {
            return getXAAt(x);
        }
    }

    public double getYAAt(int y) throws OutOfGraphBoundsException {
        if (isYInGraphRange(y)) {
            return ((y * 1.0 - stopY) / yAScale - minYA) * -1;
        } else throw new OutOfGraphBoundsException("yA");
    }

    public double getYBAt(int y) throws OutOfGraphBoundsException {
        if (isYInGraphRange(y)) {
            return ((y * 1.0 - stopY) / yBScale - minYB) * -1;
        } else throw new OutOfGraphBoundsException("yB");
    }

    public double getYAt(int y, boolean allocateToSecondYAxis) throws OutOfGraphBoundsException {
        if(allocateToSecondYAxis) {
            return getYBAt(y);
        } else {
            return getYAAt(y);
        }
    }

    public void addFunction(GraphFunction graphFunction) {
        graphFunctions.add(graphFunction);
        updateSecondAxes();
        repaint();
    }

    public void addMarker(GraphMarker graphMarker) {
        graphMarkers.add(graphMarker);
        updateSecondAxes();
        repaint();
    }

    public void addOval(GraphOval graphOval) {
        graphOvals.add(graphOval);
        updateSecondAxes();
        repaint();
    }

    public void addPoint(GraphPoint graphPoint) {
        graphPoints.add(graphPoint);
        updateSecondAxes();
        repaint();
    }

    public void addPolygon(GraphPolygon graphPolygon) {
        graphPolygons.add(graphPolygon);
        updateSecondAxes();
        repaint();
    }

    public void addRow(GraphRow graphRow) {
        graphRows.add(graphRow);
        updateSecondAxes();
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

    public void setMinimumSize (Dimension size) {
        this.minimumSize = size;
    }

    @Override
    public Dimension getMinimumSize() {
        return minimumSize;
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

    public void setDrawXAHatchMarks(boolean drawXAHatchMarks) {
        this.drawXAHatchMarks = drawXAHatchMarks;
        repaint();
    }

    public boolean isDrawXAHatchMarks() {
        return drawXAHatchMarks;
    }

    public void setDrawXBHatchMarks(boolean drawXBHatchMarks) {
        this.drawXBHatchMarks = drawXBHatchMarks;
        repaint();
    }

    public boolean isDrawXBHatchMarks() {
        return drawXBHatchMarks;
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

    public void setDrawXALabels(boolean drawXALabels) {
        this.drawXALabels = drawXALabels;
        repaint();
    }

    public boolean isDrawXALabels() {
        return drawXALabels;
    }

    public void setDrawXBLabels(boolean drawXBLabels) {
        this.drawXBLabels = drawXBLabels;
        repaint();
    }

    public boolean isDrawXBLabels() {
        return drawXBLabels;
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

    public void setLabelSecondColour(Color labelSecondColour) {
        this.labelSecondColour = labelSecondColour;
    }

    public Color getLabelSecondColour() {
        return labelSecondColour;
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

    public void setUiColour(Color uiColour) {
        this.uiColour = uiColour;
    }

    public Color getUiColour() {
        return uiColour;
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

    public void setMinXA(double minXA) {
        this.minXA = minXA;
        repaint();
    }

    public double getMinXA() {
        return minXA;
    }

    public void setMinXB(double minXB) {
        this.minXB = minXB;
        repaint();
    }

    public double getMinXB() {
        return minXB;
    }

    public void setMaxXA(double maxXA) {
        this.maxXA = maxXA;
        repaint();
    }

    public double getMaxXA() {
        return maxXA;
    }

    public void setMaxXB(double maxXB) {
        this.maxXB = maxXB;
        repaint();
    }

    public double getMaxXB() {
        return maxXB;
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

    public BufferedImage renderToImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        paint(g2d);
        g2d.dispose();
        return image;
    }

    public Dimension getGraphSize() {
        return new Dimension(stopX - startX, stopY - startY);
    }

    private static class OutOfGraphBoundsException extends Exception {
        public OutOfGraphBoundsException(String value) {
            super(String.format("%s is out of graph bounds", value));
        }
    }

    private class GraphListener implements MouseMotionListener, MouseListener, MouseWheelListener {
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

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }
    }
}
