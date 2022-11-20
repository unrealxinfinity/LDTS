package pt.up.fe.edu.hero.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pt.up.fe.edu.hero.model.Position;

import java.io.IOException;

public class LanternaGUI implements GUI{
    private final Screen screen;

    public LanternaGUI(Screen screen) {this.screen = screen;}

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

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;

        if (keyStroke.getKeyType() == KeyType.Escape) return ACTION.PAUSE;

        return ACTION.NONE;
    }

    @Override
    public void drawBoulder(Position position, Boolean isInTarget) {
        if (isInTarget) drawCharacter(position, 'B', "#D05E3B");
        else drawCharacter(position, 'B', "#362F2D");
    }

    @Override
    public void drawDozer(Position position) {
        drawCharacter(position, 'D', "#F3DF2B");
    }

    @Override
    public void drawTarget(Position position) {
        drawCharacter(position, 'T', "#FF0000");
    }

    @Override
    public void drawWall(Position position) {
        drawCharacter(position,'#', "#E0E0E0");
    }

    private void drawCharacter(Position position, Character c, String colour) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(colour));
        graphics.putString(position.getX(), position.getY() +1, c.toString());
    }
}
