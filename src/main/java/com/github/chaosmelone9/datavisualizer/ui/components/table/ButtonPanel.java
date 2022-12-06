package com.github.chaosmelone9.datavisualizer.ui.components.table;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel(Table table) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel cellIndicator = new JLabel("XX: ");
        add(cellIndicator);
        JTextField textField = new JTextField();
        add(textField);
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> table.tablePanel.setSelectedValue(Double.parseDouble(textField.getText())));
        add(applyButton);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> table.tablePanel.clearSelectedValue());
        add(clearButton);
        JButton addRowButton = new JButton("Add Row");
        add(addRowButton);
        JButton optionsButton = new JButton("Options");
        add(optionsButton);

        table.addCellSelectedListener((row, column) -> {
            cellIndicator.setText(row + ":" + column);
            try {
                textField.setText(table.tablePanel.getSelectedValue().toString());
            } catch (NullPointerException ignored) {
                textField.setText("");
            }
        });
    }
}
