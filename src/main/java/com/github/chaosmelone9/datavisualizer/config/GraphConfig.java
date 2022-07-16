package com.github.chaosmelone9.datavisualizer.config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphConfig {
    public static final String DEFAULT_TITLE = null;

    public static final int DEFAULT_PADDING = 20;
    public static final int DEFAULT_LABEL_PADDING = 30;
    public static final int DEFAULT_TITLE_PADDING = 30;

    public static final int DEFAULT_POINT_WIDTH = 6;
    public static final int DEFAULT_NUMBER_X_DIVISIONS = 100;
    public static final int DEFAULT_NUMBER_Y_DIVISIONS = 100;

    public static final boolean DEFAULT_DRAW_X_GRID = true;
    public static final boolean DEFAULT_DRAW_Y_GRID = true;
    public static final boolean DEFAULT_DRAW_X_HATCH_MARKS = true;
    public static final boolean DEFAULT_DRAW_YA_HATCH_MARKS = true;
    public static final boolean DEFAULT_DRAW_YB_HATCH_MARKS = true;
    public static final boolean DEFAULT_DRAW_X_LABELS = true;
    public static final boolean DEFAULT_DRAW_YA_LABELS = true;
    public static final boolean DEFAULT_DRAW_YB_LABELS = true;
    public static final boolean DEFAULT_INDICATE_MOUSE_X = false;
    public static final boolean DEFAULT_INDICATE_MOUSE_Y = false;
    public static final boolean DEFAULT_LABEL_MOUSE_XY = true;

    public static final Color DEFAULT_BACKGROUND_COLOUR = Color.WHITE;
    public static final Color DEFAULT_GRID_COLOUR = Color.BLACK;
    public static final Color DEFAULT_LABEL_COLOUR = Color.BLACK;
    public static final Color DEFAULT_TITLE_COLOUR = Color.BLACK;
    public static final Color DEFAULT_AXIS_COLOUR = Color.BLACK;
    public static final Color DEFAULT_HATCH_MARK_COLOUR = Color.BLACK;
    public static final Color DEFAULT_INDICATOR_COLOUR = Color.BLACK;

    public static final BufferedImage DEFAULT_BACKGROUND_IMAGE = null;

    public static final Stroke DEFAULT_GRAPH_STROKE = new BasicStroke(2f);
    public static final Stroke DEFAULT_UI_STROKE = new BasicStroke(1f);

    public static final int DEFAULT_MIN_X = 0;
    public static final int DEFAULT_MAX_X = 10;
    public static final int DEFAULT_MIN_YA = 0;
    public static final int DEFAULT_MIN_YB = 0;
    public static final int DEFAULT_MAY_YA = 10;
    public static final int DEFAULT_MAX_YB = 100;
}
