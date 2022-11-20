package pt.up.fe.edu.hero.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.hero.model.Position;

import java.io.IOException;

public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics graphics;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        gui = new LanternaGUI(screen);
    }

    @Test
    public void closeTest() throws IOException {
        gui.close();

        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void refreshTest() throws IOException {
        gui.refresh();

        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    public void clearTest() throws IOException {
        gui.clear();

        Mockito.verify(screen, Mockito.times(1)).clear();
    }

    @Test
    void drawDozer() {
        gui.drawDozer(new Position(1, 1));

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F3DF2B"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "D");
    }

    @Test
    void drawBoulderInTarget() {
        gui.drawBoulder(new Position(1, 1), true);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#D05E3B"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "B");
    }

    @Test
    void drawBoulderOffTarget() {
        gui.drawBoulder(new Position(1, 1), false);

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#362F2D"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "B");
    }

    @Test
    void drawWall() {
        gui.drawWall(new Position(1, 1));

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#E0E0E0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "#");
    }

    @Test
    void drawTarget() {
        gui.drawTarget(new Position(1, 1));

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "T");
    }
}
