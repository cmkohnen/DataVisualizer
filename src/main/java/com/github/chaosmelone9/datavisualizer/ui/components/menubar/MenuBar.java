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
