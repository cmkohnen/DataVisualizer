package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.windows.AboutWindow;
import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelpMenu extends Menu {
    public HelpMenu(MainWindow window) {
        super(window, "Help");

        add(new MenuItem("Github page", a -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/ChaosMelone9/DataVisualizer"));
            } catch (IOException | URISyntaxException ex) {
                window.getInstance().getLogger().logStackTrace(ex);
            }
        }));

        add(new MenuItem("about", e -> {
            try {
                new AboutWindow(window);
            } catch (IOException ex) {
                window.getInstance().getLogger().logStackTrace(ex);
            }
        }));
    }
}
