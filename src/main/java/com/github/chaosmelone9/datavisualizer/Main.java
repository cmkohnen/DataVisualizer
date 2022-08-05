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
package com.github.chaosmelone9.datavisualizer;

import com.github.chaosmelone9.datavisualizer.config.Config;
import com.github.chaosmelone9.datavisualizer.dataStorage.DataDirectory;
import com.github.chaosmelone9.datavisualizer.resources.ResourceFetcher;
import com.github.chaosmelone9.datavisualizer.ui.windows.ErrorWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.swing.*;
import java.io.File;
import java.util.Optional;

public class Main {
    private final DataDirectory dataDirectory;
    private Config config;
    private final ResourceFetcher fetcher;
    private Logger logger;
    public static void main(String[] args) {
        new Main(args);
    }

    public static void createNewGUIInstance() {
        new Main(new String[]{});
    }

    public Config getConfig() {
        return this.config;
    }

    public ResourceFetcher getFetcher() {
        return fetcher;
    }

    public Logger getLogger() {
        return logger;
    }

    private Main(String[] args) {
        this.dataDirectory = new DataDirectory();
        this.fetcher = new ResourceFetcher();
        if(args.length > 0) {
            try {
                initCLI(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                //UIManager.setLookAndFeel("UIManager.getSystemLookAndFeelClassName()");
                this.dataDirectory.initDirectory();
                this.config = Config.load(new File(dataDirectory.getDirectory(), "config.yml"));
                this.logger = config.getLogger();
                initGUI();
            } catch (Exception e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ignored) {}
                new ErrorWindow(true, "Something went wrong", e, false);
            }
        }

    }

    private void initGUI() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new MainWindow(this);
    }

    private void initCLI(String[] args) throws Exception {
        if(args[0].equals("--about")) {
            logger.echo(fetcher.fetchTextFromFile("about.txt"));
        }
    }
}
