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

import com.github.chaosmelone9.datavisualizer.resources.ResourceFetcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AboutWindow extends JDialog {
    MainWindow window;

    public AboutWindow(MainWindow window) throws IOException {
        super(window, "about", true);
        this.window = window;
        setLocationRelativeTo(window);
        setSize((int) (window.getWidth() * .25), (int) (window.getHeight() * .25));
        setResizable(false);
        getRootPane().setContentPane(new Content());
        setVisible(true);
    }

    private class Content extends JPanel {
        private Content() {
            super();
            setLayout(new BorderLayout());
            JButton licenseButton = new JButton("License");
            licenseButton.addActionListener(e -> {
                try {
                    new LicenseWindow(window);
                } catch (IOException ex) {
                    window.getInstance().getLogger().logStackTrace(ex);
                    new ErrorWindow(false, ex.getMessage(), ex, false);
                }
                SwingUtilities.getWindowAncestor(licenseButton).dispose();
            });
            add(licenseButton, BorderLayout.SOUTH);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            try {
                ResourceFetcher fetcher = window.getInstance().getFetcher();
                Image icon = ImageIO.read(fetcher.fetch("icon.png"));
                graphics.drawImage(icon, 10, 10, this);

                graphics.drawString(fetcher.fetchTextFromFile("about.txt"), 10, icon.getHeight(this) + 20);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
