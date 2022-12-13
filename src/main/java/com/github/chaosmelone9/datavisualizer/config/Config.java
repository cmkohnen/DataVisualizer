// SPDX-License-Identifier: GPL-3.0-or-later
/*
*  DataVisualizer
*  Copyright (C) 2022 Christoph Kohnen <christoph.kohnen@gymbane.eu>
*
*  This program is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.github.chaosmelone9.datavisualizer.config;

import com.github.chaosmelone9.datavisualizer.Logger;
import com.github.chaosmelone9.datavisualizer.dataStorage.DataDirectory;
import com.github.chaosmelone9.datavisualizer.dataStorage.FileInteractor;
import com.github.chaosmelone9.datavisualizer.dataStorage.JSONConverter;

import java.io.File;

public class Config {
    public Config() {}

    public static Config init(DataDirectory dataDirectory) throws Exception {
        // TODO check for existing config or write one to location. Also check for args later
        return JSONConverter.convertConfigFromJSON(FileInteractor.getStringFromFile(new File(dataDirectory.getDirectory(), "config.json")));
    }

    public Logger getLogger() {
        return new Logger();
    }
}
