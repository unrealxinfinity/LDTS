package pt.up.fe.edu.dozer.controller.MenuController;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.menu.MenuController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.menu.MainMenu;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuControllerTest {
    MenuController behaviourCheck;
    MenuController InternalsCheck;
    MenuController valuesCheck;
    MainGame game;
    LanternaGUI gui;
    Screen screen;
    MainMenu menuMock;
    TextGraphics graphics;
    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        behaviourCheck= Mockito.mock(MenuController.class);
        menuMock=Mockito.mock(MainMenu.class);
        InternalsCheck=new MenuController(menuMock); // Passar para construtor para testar se o mock for chamado dentro dos metodos

        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        game=Mockito.mock(MainGame.class);
        gui=Mockito.mock(LanternaGUI.class);


    }
    @Test
    public void menuControllerTest(){
        //Assertions.assertEquals(valuesCheck.getModel().getNumberEntries(),3);g
    }
    @Test
    public void stepTest() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN);
        InternalsCheck.step(game,gui.getNextAction(),0);
        Mockito.verify(menuMock,Mockito.times(1)).nextEntry();
        Mockito.verify(gui,Mockito.times(1)).getNextAction();
    }
}
