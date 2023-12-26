package org.shoebob;

import org.shoebob.gui.GuiEngine;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        GuiEngine engine = new GuiEngine();

        try {
            engine.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        engine.initUI();

        engine.println("Hello world!", 1, 0);
    }
}
