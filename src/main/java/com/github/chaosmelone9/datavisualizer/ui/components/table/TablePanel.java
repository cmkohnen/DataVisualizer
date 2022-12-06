package com.github.chaosmelone9.datavisualizer.ui.components.table;

import com.github.chaosmelone9.datavisualizer.ui.Adwaita;
import com.github.chaosmelone9.datavisualizer.ui.GraphData.GraphDataSet;
import com.github.chaosmelone9.datavisualizer.ui.components.graph.Objects.GraphRow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

public class TablePanel extends JPanel {
    private static final int ROW_HEIGHT = 20;
    private static final int COLUMN_WIDTH = 100;
    private final GraphDataSet dataSet;
    protected double startValue = 1;
    protected double stepSize = 1;
    protected int displayed_rows = 100;
    protected int selected_x = 1;
    protected int selected_y = 4;
    protected Color staticCellColour = Adwaita.LIGHT3;
    private int cell_x = 0;
    private int cell_y = 0;
    private Graphics2D g2;

    public TablePanel(Table table, GraphDataSet graphDataSet) {
        super();
        this.dataSet = graphDataSet;
        graphDataSet.addListener((type, object) -> repaint());
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clicked_x = e.getX() / COLUMN_WIDTH;
                int clicked_y = e.getY() / ROW_HEIGHT;
                if (clicked_x > 0 && clicked_y > 1) {
                    selected_x = clicked_x;
                    selected_y = clicked_y;
                    repaint();
                    table.cellSelectedListeners.forEach(listener -> listener.onCellSelected(selected_x, selected_y));
                }
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
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.g2 = (Graphics2D) graphics;
        List<GraphRow> rows = dataSet.getGraphRows();
        cell_x = 0;
        cell_y = 0;
        int width = (rows.size() + 1) * COLUMN_WIDTH;
        int height = (displayed_rows + 2) * ROW_HEIGHT;
        //you might think, this is redundant, but java somehow doesn't like it if you don't do it
        Dimension dimension = new Dimension(width, height);
        setPreferredSize(dimension);
        setSize(dimension);
        fillCell(staticCellColour);
        drawCell("RowNumber");
        cell_y++;
        fillCell(staticCellColour);
        drawCell("RowName");
        for (int i = 0; i < displayed_rows; i++) {
            cell_y = i + 2;
            fillCell(staticCellColour);
            drawCell(Double.toString(getRowValue(i)));
        }
        for (int i = 0; i < rows.size(); i++) {
            GraphRow row = rows.get(i);
            cell_x++;
            cell_y = 0;
            fillCell(staticCellColour);
            drawCell(Integer.toString(i + 1));
            cell_y++;
            fillCell(staticCellColour);
            drawCell(row.name);
            for (int i1 = 0; i1 < displayed_rows; i1++) {
                cell_y = i1 + 2;
                double keyValue = getRowValue(i1);
                Map<Double, Double> points = row.row.points;
                if (points.containsKey(keyValue)) {
                    drawCell(Double.toString(points.get(keyValue)));
                }
            }
        }
        g2.setColor(Adwaita.DARK5);
        for (int i = 0; i < rows.size(); i++) {
            g2.drawLine((i + 1) * COLUMN_WIDTH, 0, (i + 1) * COLUMN_WIDTH, height);
        }
        for (int i = 0; i < displayed_rows + 1; i++) {
            graphics.drawLine(0, (i + 1) * ROW_HEIGHT, width, (i + 1) * ROW_HEIGHT);
        }
        g2.setColor(Adwaita.RED3);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(selected_x * COLUMN_WIDTH, selected_y * ROW_HEIGHT, COLUMN_WIDTH, ROW_HEIGHT);
        g2.dispose();
        graphics.dispose();
    }

    private void drawCell(String text) {
        g2.setColor(Adwaita.DARK5);
        g2.drawString(text, cell_x * COLUMN_WIDTH + 5, cell_y * ROW_HEIGHT + 15);
    }

    private void fillCell(Color colour) {
        g2.setColor(colour);
        g2.fillRect(cell_x * COLUMN_WIDTH, cell_y * ROW_HEIGHT, COLUMN_WIDTH, ROW_HEIGHT);
    }

    private double getRowValue(int row) {
        return (double) row * stepSize + startValue;
    }

    protected Double getSelectedValue() {
        return dataSet.getGraphRows().get(selected_x - 1).row.points.get(getRowValue(selected_y - 2));
    }

    protected void setSelectedValue(Double value) {
        dataSet.getGraphRows().get(selected_x - 1).row.points.put(getRowValue(selected_y - 2), value);
        dataSet.dataChanged();
    }

    protected void clearSelectedValue() {
        dataSet.getGraphRows().get(selected_x - 1).row.points.remove(getRowValue(selected_y - 2));
        dataSet.dataChanged();
    }
}
