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

import com.github.chaosmelone9.datavisualizer.Main;
import com.github.chaosmelone9.datavisualizer.ui.components.contentpane.ContentPane;
import com.github.chaosmelone9.datavisualizer.ui.components.menubar.MenuBar;
import com.github.chaosmelone9.datavisualizer.ui.components.optionpane.OptionPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainWindow extends JFrame {

    private final Main instance;
    private final OptionPane optionPane;
    private final ContentPane contentPane;
    private final JSplitPane splitPane;

    private final MenuBar menuBar;
    public int dividerLocation = 200;

    private final List<PopupWindow> popupWindows = new ArrayList<>();

    public MainWindow(Main instance) throws IOException {
        super();
        this.instance = instance;
        this.optionPane = new OptionPane();
        this.contentPane = new ContentPane(this);
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionPane, contentPane);
        this.menuBar = new MenuBar(this);
        splitPane.setDividerLocation(dividerLocation);

        setTitle("DataVisualizer");
        setLocationRelativeTo(null);
        setSize(1000,800);
        setIconImage(ImageIO.read(instance.getFetcher().fetch("icon.png")));
        setContentPane(splitPane);
        setJMenuBar(menuBar);
        setVisible(true);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                instance.getLogger().log("Closing application");
                for (PopupWindow popupWindow : popupWindows) {
                    popupWindow.dispose();
                }
                e.getWindow().dispose();
            }
        });
    }

    public Main getInstance() {
        return instance;
    }

    public void toggleOptionPane(boolean enabled) {
        if(!enabled) {
            dividerLocation = splitPane.getDividerLocation();
        } else {
            splitPane.setDividerLocation(dividerLocation);
        }
        splitPane.setEnabled(enabled);
        optionPane.setVisible(enabled);
    }

    public OptionPane getOptionPane() {
        return optionPane;
    }

    public ContentPane getContentPane() {
        return contentPane;
    }

    @Override
    public MenuBar getJMenuBar() {
        return menuBar;
    }

    public void registerPopupWindow(PopupWindow popupWindow) {
        popupWindows.add(popupWindow);
    }
}
