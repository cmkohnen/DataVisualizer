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

import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final FileMenu fileMenu;
    private final ViewMenu viewMenu;
    private final ToolsMenu toolsMenu;
    private final HelpMenu helpMenu;

    public MenuBar(MainWindow window) {
        this.fileMenu = new FileMenu(window);
        this.viewMenu = new ViewMenu(window);
        this.toolsMenu = new ToolsMenu(window);
        this.helpMenu = new HelpMenu(window);

        register(fileMenu);
        register(viewMenu);
        register(toolsMenu);
        register(helpMenu);
    }

    private void register(JMenu menu) {
        this.add(menu);
    }

    public FileMenu getFileMenu() {
        return fileMenu;
    }

    public ViewMenu getViewMenu() {
        return viewMenu;
    }

    public ToolsMenu getToolsMenu() {
        return toolsMenu;
    }

    public HelpMenu getHelpMenu() {
        return helpMenu;
    }
}
