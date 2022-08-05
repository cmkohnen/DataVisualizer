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

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String DEBUG_PATTERN = "[debug] ";
    private static final String INFO_PATTERN = "[info] ";
    private static final String ERROR_PATTERN = "[ERROR] ";

    public void log(Object o) {
        print(ANSI_RESET + o);
    }

    public void debug(Object o) {
        print(ANSI_RESET + DEBUG_PATTERN + o);
    }

    public void info(Object o) {
        print(ANSI_YELLOW + INFO_PATTERN + o);
    }

    public void echo(Object o) {
        print(ANSI_RESET + o);
    }

    public void error(Object o) {
        print(ANSI_RED + ERROR_PATTERN + o);
    }

    public void logStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        throwable.printStackTrace();
    }

    private void print(Object o) {
        System.out.println(o);
    }
}
