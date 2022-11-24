package pt.up.fe.edu.hero.viewer;


import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.gui.LanternaGUI;
import pt.up.fe.edu.hero.model.Position;
import pt.up.fe.edu.hero.model.menu.MainMenu;
import pt.up.fe.edu.hero.model.menu.Menu;
import pt.up.fe.edu.hero.viewer.menu.MenuViewer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class MenuViewerTest {
    private LanternaGUI gui;
    private Screen screen;
    private TextGraphics graphics;
    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        gui = new LanternaGUI(screen);
    }

    @Test
    public void drawElementTest() {
        Menu main = new MainMenu();
        MenuViewer menu= new MenuViewer(main);
        menu.drawElements(gui);
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(graphics, Mockito.times(1)).putString(10, 10, "Main Menu");
        Mockito.verify(graphics, Mockito.times(1)).putString(10,13 ,"Level Select");
        Mockito.verify(graphics, Mockito.times(1)).putString(10,14 ,"Level Editor");
        Mockito.verify(graphics, Mockito.times(1)).putString(10,15 ,"Quit");
        Mockito.verify(graphics, Mockito.times(4)).setForegroundColor(TextColor.Factory.fromString(Mockito.any()));
    }
}