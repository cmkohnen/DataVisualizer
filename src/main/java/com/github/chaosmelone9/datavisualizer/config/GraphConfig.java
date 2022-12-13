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
package com.github.chaosmelone9.datavisualizer.config;

import com.github.chaosmelone9.datavisualizer.CustomGSON;
import com.github.chaosmelone9.datavisualizer.ui.Adwaita;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.Graph;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class GraphConfig {
    public static final String DEFAULT_TITLE = null;

    public static final Dimension DEFAULT_MINIMUM_SIZE = new Dimension(500, 350);
    public static final int DEFAULT_PADDING = 20;
    public static final int DEFAULT_LABEL_PADDING = 30;
    public static final int DEFAULT_TITLE_PADDING = 30;

    public static final int DEFAULT_POINT_RADIUS = 6;
    public static final int DEFAULT_NUMBER_X_DIVISIONS = 100;
    public static final int DEFAULT_NUMBER_Y_DIVISIONS = 100;

    public static final boolean DEFAULT_DRAW_X_GRID = true;
    public static final boolean DEFAULT_DRAW_Y_GRID = true;
    public static final boolean DEFAULT_DRAW_XA_HATCH_MARKS = true;
    public static final boolean DEFAULT_DRAW_XB_HATCH_MARKS = true;
    public static final boolean DEFAULT_DRAW_YA_HATCH_MARKS = true;
    public static final boolean DEFAULT_DRAW_YB_HATCH_MARKS = true;
    public static final boolean DEFAULT_DRAW_XA_LABELS = true;
    public static final boolean DEFAULT_DRAW_XB_LABELS = true;
    public static final boolean DEFAULT_DRAW_YA_LABELS = true;
    public static final boolean DEFAULT_DRAW_YB_LABELS = true;
    public static final boolean DEFAULT_INDICATE_MOUSE_X = false;
    public static final boolean DEFAULT_INDICATE_MOUSE_Y = false;
    public static final boolean DEFAULT_LABEL_MOUSE_XY = true;

    public static final Color DEFAULT_BACKGROUND_COLOUR = Adwaita.LIGHT1;
    public static final Color DEFAULT_GRID_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_LABEL_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_SECOND_LABEL_COLOUR = Adwaita.RED5;
    public static final Color DEFAULT_TITLE_COLOUR = Adwaita.BLUE5;
    public static final Color DEFAULT_AXIS_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_HATCH_MARK_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_INDICATOR_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_UI_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_UI_BACKGROUND_COLOUR = Adwaita.LIGHT2;

    public static final BufferedImage DEFAULT_BACKGROUND_IMAGE = null;

    public static final Stroke DEFAULT_GRAPH_STROKE = new BasicStroke(2f);
    public static final Stroke DEFAULT_UI_STROKE = new BasicStroke(1f);

    public static final int DEFAULT_MIN_XA = 0;
    public static final int DEFAULT_MIN_XB = 0;
    public static final int DEFAULT_MAX_XA = 10;
    public static final int DEFAULT_MAX_XB = 100;
    public static final int DEFAULT_MIN_YA = 0;
    public static final int DEFAULT_MIN_YB = 0;
    public static final int DEFAULT_MAY_YA = 10;
    public static final int DEFAULT_MAX_YB = 100;

    public static final double DEFAULT_ZOOM_FACTOR = 0.1;

    public static final Color DEFAULT_OVAL_COLOUR = Adwaita.BLUE5;
    public static final Color DEFAULT_POINT_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_ROW_COLOUR = Adwaita.DARK5;
    public static final Color DEFAULT_POLYGON_COLOUR = Adwaita.DARK5;

    public static String readJSONFromGraph(Graph graph) throws ClassCastException {
        AbstractGraph abstractGraph = new AbstractGraph();
        abstractGraph.title = graph.getTitle();
        abstractGraph.minimumSize = graph.getMinimumSize();
        abstractGraph.padding = graph.getPadding();
        abstractGraph.labelPadding = graph.getLabelPadding();
        abstractGraph.titlePadding = graph.getTitlePadding();
        abstractGraph.pointRadius = graph.getPointRadius();
        abstractGraph.numberXDivisions = graph.getNumberXDivisions();
        abstractGraph.numberYDivisions = graph.getNumberYDivisions();
        abstractGraph.drawXGrid = graph.isDrawXGrid();
        abstractGraph.drawYGrid = graph.isDrawYGrid();
        abstractGraph.drawXAHatchMarks = graph.isDrawXAHatchMarks();
        abstractGraph.drawXBHatchMarks = graph.isDrawXBHatchMarks();
        abstractGraph.drawYAHatchMarks = graph.isDrawYAHatchMarks();
        abstractGraph.drawYBHatchMarks = graph.isDrawYBHatchMarks();
        abstractGraph.drawXALabels = graph.isDrawXALabels();
        abstractGraph.drawXBLabels = graph.isDrawXBLabels();
        abstractGraph.drawYALabels = graph.isDrawYALabels();
        abstractGraph.drawYBLabels = graph.isDrawYBLabels();
        abstractGraph.indicateMouseX = graph.isIndicateMouseX();
        abstractGraph.indicateMouseY = graph.isIndicateMouseY();
        abstractGraph.labelMouseXY = graph.isLabelMouseXY();
        abstractGraph.backgroundColour = graph.getBackgroundColour();
        abstractGraph.gridColour = graph.getGridColour();
        abstractGraph.labelColour = graph.getLabelColour();
        abstractGraph.secondLabelColour = graph.getSecondLabelColour();
        abstractGraph.titleColour = graph.getTitleColour();
        abstractGraph.axisColour = graph.getAxisColour();
        abstractGraph.hatchMarkColour = graph.getHatchMarkColour();
        abstractGraph.indicatorColour = graph.getIndicatorColour();
        abstractGraph.uiColour = graph.getUiColour();
        abstractGraph.uiBackgroundColour = graph.getUiBackgroundColour();
        return CustomGSON.GSON.toJson(abstractGraph);
    }

    public static void applyJSONToGraph(String object, Graph graph) throws ClassCastException {
        AbstractGraph abstractGraph = CustomGSON.GSON.fromJson(object, AbstractGraph.class);
        graph.setTitle(abstractGraph.title);
        graph.setMinimumSize(abstractGraph.minimumSize);
        graph.setPadding(abstractGraph.padding);
        graph.setLabelPadding(abstractGraph.labelPadding);
        graph.setTitlePadding(abstractGraph.titlePadding);
        graph.setPointRadius(abstractGraph.pointRadius);
        graph.setNumberXDivisions(abstractGraph.numberXDivisions);
        graph.setNumberYDivisions(abstractGraph.numberYDivisions);
        graph.setDrawXGrid(abstractGraph.drawXGrid);
        graph.setDrawYGrid(abstractGraph.drawYGrid);
        graph.setDrawXAHatchMarks(abstractGraph.drawXAHatchMarks);
        graph.setDrawXBHatchMarks(abstractGraph.drawXBHatchMarks);
        graph.setDrawYAHatchMarks(abstractGraph.drawYAHatchMarks);
        graph.setDrawYBHatchMarks(abstractGraph.drawYBHatchMarks);
        graph.setDrawXALabels(abstractGraph.drawXALabels);
        graph.setDrawXBLabels(abstractGraph.drawXBLabels);
        graph.setDrawYALabels(abstractGraph.drawYALabels);
        graph.setDrawYBLabels(abstractGraph.drawYBLabels);
        graph.setIndicateMouseX(abstractGraph.indicateMouseX);
        graph.setIndicateMouseY(abstractGraph.indicateMouseY);
        graph.setLabelMouseXY(abstractGraph.labelMouseXY);
        graph.setBackgroundColour(abstractGraph.backgroundColour);
        graph.setGridColour(abstractGraph.gridColour);
        graph.setLabelColour(abstractGraph.labelColour);
        graph.setSecondLabelColour(abstractGraph.secondLabelColour);
        graph.setTitleColour(abstractGraph.titleColour);
        graph.setAxisColour(abstractGraph.axisColour);
        graph.setHatchMarkColour(abstractGraph.hatchMarkColour);
        graph.setIndicatorColour(abstractGraph.indicatorColour);
        graph.setUiColour(abstractGraph.uiColour);
        graph.setUiBackgroundColour(abstractGraph.uiBackgroundColour);
    }

    private static class AbstractGraph implements Serializable {
        public String title;
        public Dimension minimumSize;
        public int padding;
        public int labelPadding;
        public int titlePadding;
        public int pointRadius;
        public int numberXDivisions;
        public int numberYDivisions;
        public boolean drawXGrid;
        public boolean drawYGrid;
        public boolean drawXAHatchMarks;
        public boolean drawXBHatchMarks;
        public boolean drawYAHatchMarks;
        public boolean drawYBHatchMarks;
        public boolean drawXALabels;
        public boolean drawXBLabels;
        public boolean drawYALabels;
        public boolean drawYBLabels;
        public boolean indicateMouseX;
        public boolean indicateMouseY;
        public boolean labelMouseXY;
        public Color backgroundColour;
        public Color gridColour;
        public Color labelColour;
        public Color secondLabelColour;
        public Color titleColour;
        public Color axisColour;
        public Color hatchMarkColour;
        public Color indicatorColour;
        public Color uiColour;
        public Color uiBackgroundColour;
    }
}
