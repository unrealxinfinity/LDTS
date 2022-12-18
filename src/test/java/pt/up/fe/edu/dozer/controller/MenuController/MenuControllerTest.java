package pt.up.fe.edu.dozer.controller.MenuController;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.sun.source.tree.ModuleTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.controller.menu.MenuController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.menu.MainMenu;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuControllerTest {
    MenuController behaviourCheck;
    MenuController InternalsCheck;
    MenuController valuesCheck;
    MainGame gameMock;
    LanternaGUI gui;
    Screen screen;
    MainMenu menuMock;
    AudioManager audiomock;
    TextGraphics graphics;
    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        behaviourCheck= Mockito.mock(MenuController.class);
        menuMock=Mockito.mock(MainMenu.class);
        InternalsCheck=new MenuController(menuMock); // Passar para construtor para testar se o mock for chamado dentro dos metodos
        valuesCheck= new MenuController(new MainMenu());
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        gameMock=Mockito.mock(MainGame.class);
        gui=Mockito.mock(LanternaGUI.class);
        audiomock=Mockito.mock(AudioManager.class);


    }
    @Test
    public void menuControllerTest(){
        Assertions.assertEquals(3,valuesCheck.getModel().getNumberEntries());
        Assertions.assertNotEquals(null,valuesCheck.getSound());
    }
    @Test
    public void stepTest() throws IOException {
        InternalsCheck.step(gameMock, GUI.ACTION.DOWN);
        Mockito.verify(menuMock,Mockito.times(1)).nextEntry();

        InternalsCheck.step(gameMock,GUI.ACTION.UP);
        Mockito.verify(menuMock,Mockito.times(1)).previousEntry();

       valuesCheck.step(gameMock,GUI.ACTION.SELECT);
        Assertions.assertEquals(true,valuesCheck.getModel().isSelectedSelect());
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        gameMock=Mockito.mock(MainGame.class);

        valuesCheck.getModel().nextEntry();
        Assertions.assertEquals(true,valuesCheck.getModel().isSelectedEditor());

        valuesCheck.step(gameMock,GUI.ACTION.SELECT);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        gameMock=Mockito.mock(MainGame.class);

        valuesCheck.getModel().nextEntry();
        Assertions.assertEquals(true,valuesCheck.getModel().isSelectedQuit());

        Mockito.when(gameMock.getBGM()).thenReturn(audiomock);
        valuesCheck.step(gameMock,GUI.ACTION.SELECT);
        Mockito.verify(audiomock,Mockito.times(1)).close();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        audiomock = Mockito.mock(AudioManager.class);
        gameMock=Mockito.mock(MainGame.class);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(true);
        valuesCheck.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).resumeBGM();
        gameMock=Mockito.mock(MainGame.class);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(false);
        valuesCheck.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).muteBGM();

    }

}
