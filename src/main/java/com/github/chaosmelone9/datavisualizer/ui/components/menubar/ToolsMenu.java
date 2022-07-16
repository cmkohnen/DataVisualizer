package com.github.chaosmelone9.datavisualizer.ui.components.menubar;

import com.github.chaosmelone9.datavisualizer.ui.windows.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class ToolsMenu extends Menu{
    public ToolsMenu(MainWindow window) {
        super(window, "Tools");

        add(new MenuItem("Export Graph to Image", actionEvent -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new java.io.File("."));
            fileChooser.setDialogTitle("Choose Background Image");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp"));
            fileChooser.setSelectedFile(new File("graph.png"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(window.getContentPane().getGraph().renderToImage(), "png", fileChooser.getSelectedFile());
                } catch (IOException e) {
                    window.getInstance().getLogger().logStackTrace(e);
                }
            }
        }));
    }
}
