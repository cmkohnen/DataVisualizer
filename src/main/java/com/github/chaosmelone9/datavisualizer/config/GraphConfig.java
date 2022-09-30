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

import com.github.chaosmelone9.datavisualizer.ui.Adwaita;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.Graph;
import org.json.simple.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unchecked")
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

    public static JSONObject readJSONFromGraph(Graph graph) throws ClassCastException {
        JSONObject object = new JSONObject();
        object.put("title", graph.getTitle());
        object.put("MinimumSize", graph.getMinimumSize());
        object.put("Padding", graph.getPadding());
        object.put("LabelPadding", graph.getLabelPadding());
        object.put("TitlePadding", graph.getTitlePadding());
        object.put("PointRadius", graph.getPointRadius());
        object.put("NumberXDivisions", graph.getNumberXDivisions());
        object.put("NumberYDivisions", graph.getNumberYDivisions());
        object.put("DrawXGrid", graph.isDrawXGrid());
        object.put("DrawYGrid", graph.isDrawYGrid());
        object.put("DrawXAHatchMarks", graph.isDrawXAHatchMarks());
        object.put("DrawXBHatchMarks", graph.isDrawXBHatchMarks());
        object.put("DrawYAHatchMarks", graph.isDrawYAHatchMarks());
        object.put("DrawYBHatchMarks", graph.isDrawYBHatchMarks());
        object.put("DrawXALabels", graph.isDrawXALabels());
        object.put("DrawXBLabels", graph.isDrawXBLabels());
        object.put("DrawYALabels", graph.isDrawYALabels());
        object.put("DrawYBLabels", graph.isDrawYBLabels());
        object.put("IndicateMouseX", graph.isIndicateMouseX());
        object.put("IndicateMouseY", graph.isIndicateMouseY());
        object.put("LabelMouseXY", graph.isLabelMouseXY());
        object.put("BackgroundColour", graph.getBackgroundColour());
        object.put("GridColour", graph.getGridColour());
        object.put("LabelColour", graph.getLabelColour());
        object.put("LabelSecondColour", graph.getLabelSecondColour());
        object.put("TitleColour", graph.getTitleColour());
        object.put("AxisColour", graph.getAxisColour());
        object.put("HatchMarkColour", graph.getHatchMarkColour());
        object.put("IndicatorColour", graph.getIndicatorColour());
        object.put("UIColour", graph.getUiColour());
        object.put("UIBackgroundColour", graph.getUiBackgroundColour());
        object.put("BackgroundImage", graph.getBackgroundImage());
        object.put("GraphStroke", graph.getGraphStroke());
        object.put("UIStroke", graph.getUiStroke());
        object.put("MinXA", graph.getMinXA());
        object.put("MinXB", graph.getMinXB());
        object.put("MaxXA", graph.getMaxXA());
        object.put("MaxXB", graph.getMaxXB());
        object.put("MinYA", graph.getMinYA());
        object.put("MinYB", graph.getMinYB());
        object.put("MaxYA", graph.getMaxYA());
        object.put("MaxYB", graph.getMaxYB());
        object.put("ZoomFactor", graph.getZoomFactor());
        return object;
    }

    public static void applyJSONToGraph(JSONObject object, Graph graph) throws ClassCastException{
        graph.setTitle((String) object.get("Title"));
        graph.setMinimumSize((Dimension) object.get("MinimumSize"));
        graph.setPadding((Integer) object.get("Padding"));
        //TODO Copilot help i'm Stuck
    }
}
