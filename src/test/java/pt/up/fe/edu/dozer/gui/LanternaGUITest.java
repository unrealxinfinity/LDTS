package pt.up.fe.edu.dozer.gui;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.Position;

import java.io.IOException;

import static org.mockito.Mockito.spy;

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
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "*");
    }

    @Test
    void drawBoulderInTarget() {
        TextCharacter textChar = Mockito.mock(TextCharacter.class);
        Mockito.when(textChar.getCharacterString()).thenReturn("+");
        Mockito.when(graphics.getCharacter(Mockito.eq(1), Mockito.eq(2))).thenReturn(textChar);
        gui.drawBoulder(new Position(1, 1));

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#D05E3B"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "~");
    }

    @Test
    void drawBoulderOffTarget() {
        TextCharacter textChar = Mockito.mock(TextCharacter.class);
        Mockito.when(textChar.getCharacterString()).thenReturn(null);
        Mockito.when(graphics.getCharacter(Mockito.anyInt(), Mockito.anyInt())).thenReturn(textChar);
        gui.drawBoulder(new Position(1, 1));

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#362F2D"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "&");
    }

    @Test
    public void drawNullBoulder() {
        TextCharacter textChar = Mockito.mock(TextCharacter.class);
        Mockito.when(textChar.getCharacterString()).thenThrow(NullPointerException.class);
        Mockito.when(graphics.getCharacter(Mockito.anyInt(), Mockito.anyInt())).thenReturn(textChar);
        gui.drawBoulder(new Position(1, 1));

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#362F2D"));
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "&");
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
        Mockito.verify(graphics, Mockito.times(1)).putString(1, 2, "+");
    }

    @Test
    void drawPlacer() {
        gui.drawPlacer(new Position(2,3));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#B41EE2"));
        Mockito.verify(graphics, Mockito.times(1)).putString(2, 4, "$");
    }

    @Test
    void getNextActionNone() throws IOException {
        Mockito.when(screen.pollInput()).thenReturn(null);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.NONE, action);
    }

    @Test
    void getNextActionQuitEOF() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.EOF);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.QUIT, action);
    }

    @Test
    void getNextActionQuitQ() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(stroke.getCharacter()).thenReturn('q');
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.QUIT, action);
    }

    @Test
    void getNextActionSave() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(stroke.getCharacter()).thenReturn('s');
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.SAVE, action);
    }

    @Test
    void getNextActionMute() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(stroke.getCharacter()).thenReturn('m');
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.MUTE, action);
    }

    @Test
    void getNextActionUp() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.UP, action);
    }

    @Test
    void getNextActionLeft() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.LEFT, action);
    }

    @Test
    void getNextActionRight() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.ArrowRight);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.RIGHT, action);
    }

    @Test
    void getNextActionDown() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.ArrowDown);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.DOWN, action);
    }

    @Test
    void getNextActionPause() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Escape);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.PAUSE, action);
    }

    @Test
    void getNextActionUnknown() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Unknown);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.NONE, action);
    }

    @Test
    void getNextActionSelect() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Enter);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.SELECT, action);
    }

    @Test
    void getNextActionRestart() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(stroke.getCharacter()).thenReturn('r');
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.RESTART, action);
    }

    @Test
    void getNextActionRemove() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Backspace);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.REMOVE, action);
    }

    @Test
    void getNextActionCycle() throws IOException {
        KeyStroke stroke = Mockito.mock(KeyStroke.class);
        Mockito.when(stroke.getKeyType()).thenReturn(KeyType.Tab);
        Mockito.when(screen.pollInput()).thenReturn(stroke);

        GUI.ACTION action = gui.getNextAction();

        Assertions.assertEquals(GUI.ACTION.CYCLE, action);
    }
    @Test
    void drawTextTest() {
        gui.drawText(new Position(5,5),"Welcome","#FFFFFA");

        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFA"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "Welcome");
    }

    @Test
    void drawTimeTest() {
        gui.drawTime(new Position(5,5), 0, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "00:00");
    }

    @Test
    void drawTimeTestMinute() {
        gui.drawTime(new Position(5, 5), 120, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "02:00");
    }

    @Test
    void drawTimeTestCheckMinuteBound() {
        gui.drawTime(new Position(5, 5), 23123123, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "00:00");
    }

    @Test
    void drawTimeTestBothLengthTwo() {
        gui.drawTime(new Position(5, 5), 610, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "10:10");
    }

    @Test
    void drawTimeTestBothLengthLow() {
        gui.drawTime(new Position(5, 5), 62, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "01:02");
    }

    @Test
    void drawTimeTestMinuteLengthLow() {
        gui.drawTime(new Position(5, 5), 70, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "01:10");
    }

    @Test
    void drawTimeTestSecondLengthLow() {
        gui.drawTime(new Position(5, 5), 601, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "10:01");
    }

    @Test
    void drawTimeTestBound() {
        gui.drawTime(new Position(5, 5), 2, "#F0F0F0");
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#F0F0F0"));
        Mockito.verify(graphics, Mockito.times(1)).putString(5, 5, "00:02");
    }
}
