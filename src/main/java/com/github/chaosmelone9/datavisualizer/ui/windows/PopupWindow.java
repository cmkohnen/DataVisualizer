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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PopupWindow extends JFrame {
    protected MainWindow mainWindow;
    protected final JPanel content;
    protected final GridBagLayout layout;
    protected final GridBagConstraints constraints;

    public PopupWindow(String title, MainWindow window) {
        this.mainWindow = window;
        this.content = new JPanel();
        this.layout = new GridBagLayout();
        this.constraints = layout.getConstraints(content);
        mainWindow.registerPopupWindow(this);
        JScrollPane pane = new JScrollPane(content);
        setContentPane(pane);
        content.setLayout(layout);
        setTitle(title);
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        try {
            setIconImage(ImageIO.read(window.getInstance().getFetcher().fetch("icon.png")));
        } catch (IOException e) {
            mainWindow.getInstance().getLogger().error("Could not load icon");
        }
        setVisible(true);
    }

    protected void add(JComponent component) {
        constraints.gridy = constraints.gridy + 1;
        content.add(component, constraints);
    }

    protected void autoAdjustSize() {
        setSize((int) (mainWindow.getWidth() - mainWindow.getWidth() * .25), (int)
                (mainWindow.getHeight() - mainWindow.getHeight() * .25));
    }
}
