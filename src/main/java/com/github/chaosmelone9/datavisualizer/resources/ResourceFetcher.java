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
package com.github.chaosmelone9.datavisualizer.resources;

import java.io.IOException;
import java.io.InputStream;

public class ResourceFetcher {
    public InputStream fetch(String file) {
        return getClass().getClassLoader().getResourceAsStream(file);
    }

    public String fetchTextFromFile(String file) throws IOException {
        InputStream stream = fetch(file);
        StringBuilder output = new StringBuilder();
        int content;
        while ((content = stream.read()) != -1) {
            output.append((char) content);
        }
        return output.toString();
    }
}
