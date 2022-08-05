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
package com.github.chaosmelone9.datavisualizer.dataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class DataDirectory {
    private File directory = null;

    public void initDirectory() throws IOException {
        this.directory = new File(Paths.get("").toAbsolutePath().toString(), "DataVisualizer");
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Could not create DataVisualizer directory");
            }
        }
    }

    public DataDirectory(File directory) throws IOException {
        setDirectory(directory);
    }

    public DataDirectory() {
    }

    public File getDirectory() throws DataDirectoryNotInitialisedException {
        if(directory == null) {
            throw new DataDirectoryNotInitialisedException();
        }
        return directory;
    }

    public void setDirectory(File directory) throws FileNotFoundException {
        if (!this.directory.exists()) {
            throw new FileNotFoundException(String.format("Directory %s does not exist", directory));
        }
        this.directory = directory;
    }

    private static class DataDirectoryNotInitialisedException extends Exception {
        public DataDirectoryNotInitialisedException() {
            super("DataDirectory not initialised");
        }
    }
}
