package com.github.chaosmelone9.datavisualizer.ui;

import com.github.chaosmelone9.datavisualizer.resources.ResourceFetcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AboutWindow extends JDialog {
    ResourceFetcher fetcher;

    public AboutWindow(MainWindow window) throws IOException {
        super(window,"about", true);
        this.fetcher = window.getInstance().getFetcher();
        setLocationRelativeTo(window);
        setSize((int) (window.getWidth() * .25), (int) (window.getHeight() * .25));
        setResizable(false);
        getRootPane().setContentPane(new Content());
        setVisible(true);
    }

    private class Content extends JPanel {
        private Content() {
            super();
            setLayout(new BorderLayout());
            //add(new JTextArea(window.getInstance().getFetcher().fetchTextFromFile("about.txt")));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            try {
                Image icon = ImageIO.read(fetcher.fetch("icon.png"));
                graphics.drawImage(icon, 10, 10, this);

                graphics.drawString(fetcher.fetchTextFromFile("about.txt"),10, icon.getHeight(this) + 20);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
