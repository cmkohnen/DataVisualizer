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
package com.github.chaosmelone9.datavisualizer.ui.windows;

import com.github.chaosmelone9.datavisualizer.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class ErrorWindow extends JDialog {
    public ErrorWindow(boolean isFatal, String message, Throwable throwable, boolean logStackTrace) {
        setTitle("Error");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setSize(600, 200);
        if (isFatal) {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        add(new JLabel(message), BorderLayout.NORTH);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        if (logStackTrace) {
            new Logger().logStackTrace(throwable);
        }
        throwable.printStackTrace(pw);
        String stacktrace = sw.getBuffer().toString();
        JTextArea textArea = new JTextArea();
        textArea.setText(stacktrace);
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);
        setVisible(true);
    }

    public ErrorWindow(boolean isFatal, String message) {
        setTitle("Error");
        setLocationRelativeTo(null);
        setSize(600, 200);
        if (isFatal) {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        add(new JLabel(message));
        setVisible(true);
    }
}
