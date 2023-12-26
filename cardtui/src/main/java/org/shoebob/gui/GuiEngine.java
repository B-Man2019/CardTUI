package org.shoebob.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class GuiEngine {
    private TerminalScreen screen;

    private TextColor primary; // primary color
    private TextColor secondary; // secondary color
    private TextColor background; // background color

    public GuiEngine() {
        try {
            this.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws IOException {
        screen = new DefaultTerminalFactory().createScreen();

        primary = TextColor.ANSI.GREEN_BRIGHT;
        secondary = TextColor.ANSI.GREEN;
        background = TextColor.ANSI.BLACK;
    }

    public void start() throws IOException {
        screen.startScreen();
    }

    public void println(String msg, int row, int col) {
        for (int i = 0; i < msg.length(); i++) {
            screen.setCursorPosition(new TerminalPosition(col + i, row));

            screen.setCharacter(col + i, row, new TextCharacter(msg.charAt(i), primary, background));

            try {
                screen.refresh();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        screen.setCursorPosition(new TerminalPosition(row + 1, 0)); // set cursor to next line
    }

    public void print(String msg, int row, int col) {
        for (int i = 0; i < msg.length(); i++) {
            screen.setCursorPosition(new TerminalPosition(col + i, row));

            screen.setCharacter(col + i, row, new TextCharacter(msg.charAt(i), primary, background));

            try {
                screen.refresh();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // makes a basic header and footer on the terminal
    public void initUI() {
        screen.setCursorPosition(null); // hide the cursor

        int width = screen.getTerminalSize().getColumns() - 1;
        int height = screen.getTerminalSize().getRows() - 1;

        screen.clear();

        screen.newTextGraphics().drawLine(0, 0, width, 0, new TextCharacter(' ')
                .withForegroundColor(background).withBackgroundColor(primary)); // draw header

        screen.newTextGraphics().drawLine(0, height, width, height, new TextCharacter(' ')
                .withForegroundColor(background).withBackgroundColor(primary)); // draw footer

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
