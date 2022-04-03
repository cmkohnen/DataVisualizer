package com.github.chaosmelone9.datavisualizer.config;

import java.awt.*;

public class GraphConfig {

    public String title;

    public int padding;
    public int labelPadding;
    public int titlePadding;

    public int pointWidth;
    public int numberYDivisions;
    public int numberXDivisions;

    public boolean drawTitle;
    public boolean drawXGrid;
    public boolean drawYGrid;
    public boolean drawXHatchMarks;
    public boolean drawYAHatchMarks;
    public boolean drawYBHatchMarks;
    public boolean drawXLabels;
    public boolean drawYALabels;
    public boolean drawYBLabels;
    public boolean indicateMouseX;
    public boolean indicateMouseY;

    public Color backgroundColour;
    public Color gridColour;
    public Color labelColour;
    public Color titleColour;
    public Color axisColour;
    public Color hatchMarkColour;
    public Color indicatorColour;

    public GraphConfig() {
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
    }
}
