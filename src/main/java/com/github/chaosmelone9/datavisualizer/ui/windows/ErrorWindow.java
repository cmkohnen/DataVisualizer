package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.Logger;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class ErrorWindow extends JDialog {
    public ErrorWindow(String message, Optional<Exception> exception) {
        super();
        setTitle("Error");
        setLocationRelativeTo(null);
        setSize(600, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel(message));

        exception.ifPresent(e -> {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            e.printStackTrace(pw);
            String stacktrace = sw.getBuffer().toString();
            new Logger().logStackTrace(e);
            JTextArea textArea = new JTextArea();
            textArea.setText(stacktrace);
            textArea.setEditable(false);
            add(textArea);
        });

        setVisible(true);
    }
}
