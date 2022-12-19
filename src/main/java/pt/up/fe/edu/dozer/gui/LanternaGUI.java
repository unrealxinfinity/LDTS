package pt.up.fe.edu.dozer.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import pt.up.fe.edu.dozer.model.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }
    public LanternaGUI(int width, int height) throws URISyntaxException, IOException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        screen.refresh();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        return terminalFactory.createTerminal();
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("font/JoystixMonospace-Regular.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 20);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'r') return ACTION.RESTART;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 's') return ACTION.SAVE;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;

        if (keyStroke.getKeyType() == KeyType.Escape) return ACTION.PAUSE;
        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.SELECT;
        if (keyStroke.getKeyType() == KeyType.Tab) return ACTION.CYCLE;
        if (keyStroke.getKeyType() == KeyType.Backspace) return ACTION.REMOVE;
        if(keyStroke.getKeyType()==KeyType.Character && keyStroke.getCharacter()=='m') return ACTION.MUTE;

        return ACTION.NONE;
    }

    @Override
    public void drawBoulder(Position position) {
        try {
            if (Objects.equals(screen.newTextGraphics().getCharacter(position.getX(), position.getY() + 1).getCharacterString(), "+"))
                drawCharacter(position, '~', "#D05E3B");
            else drawCharacter(position, '&', "#362F2D");
        } catch (NullPointerException e) {
            drawCharacter(position, '&', "#362F2D");
        }
    }

    @Override
    public void drawDozer(Position position) {
        drawCharacter(position, '*', "#F3DF2B");
    }

    @Override
    public void drawTarget(Position position) {
        drawCharacter(position, '+', "#FF0000");
    }

    @Override
    public void drawWall(Position position) {
        drawCharacter(position, '#', "#E0E0E0");
    }

    private void drawCharacter(Position position, Character c, String colour) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(position.getX(), position.getY() + 1, c.toString());
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawTime(Position position, long time, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.putString(position.getX(), position.getY(), getStringTime(time));
    }

    private String getStringTime(long time) {
        long minutes = time / 60;
        if (minutes > 59) minutes = 0;
        long seconds = time - minutes * 60;
        String m = String.valueOf(minutes);

        String s = String.valueOf(seconds);
        if (m.length() < 2 && s.length() < 2) return "0" + m + ":" + "0" + s;
        else if (m.length() < 2 && s.length() == 2) return "0" + m + ":" + s;
        else if (m.length() == 2 && s.length() < 2) return m + ":" + "0" + s;
        else return m + ":" + s;
    }


    @Override
    public void drawPlacer(Position position) {
        drawCharacter(position, 'p', "#B41EE2");
    }
}
