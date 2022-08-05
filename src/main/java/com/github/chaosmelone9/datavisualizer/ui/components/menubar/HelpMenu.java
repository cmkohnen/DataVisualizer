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
package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.Logger;
import com.github.chaosmelone9.datavisualizer.ui.windows.AboutWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.ErrorWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.LicenseWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelpMenu extends Menu {
    Logger logger;
    public HelpMenu(MainWindow window) {
        super(window, "Help");
        this.logger = window.getInstance().getLogger();

        add(new MenuItem("Github page", e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/ChaosMelone9/DataVisualizer"));
            } catch (IOException | URISyntaxException ex) {
                logger.logStackTrace(ex);
                new ErrorWindow(false, ex.getMessage(), ex, false);
            }
        }));

        add(new MenuItem("License", e -> {
            try {
                new LicenseWindow(window);
            } catch (IOException ex) {
                logger.logStackTrace(ex);
                new ErrorWindow(false, ex.getMessage(), ex, false);
            }
        }));

        add(new MenuItem("about", e -> {
            try {
                new AboutWindow(window);
            } catch (IOException ex) {
                logger.logStackTrace(ex);
                new ErrorWindow(false, ex.getMessage(), ex, false);
            }
        }));
    }
}
