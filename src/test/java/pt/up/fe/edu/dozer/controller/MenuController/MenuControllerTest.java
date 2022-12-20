package pt.up.fe.edu.dozer.controller.MenuController;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.sun.source.tree.ModuleTree;
import org.junit.jupiter.api.AfterEach;
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
    MenuController spyCheck;
    MenuController valuesCheck;
    MainGame gameMock;
    LanternaGUI gui;
    Screen screen;
    MainMenu menuMock;
    AudioManager audiomock;
    AudioManager audioSpy;
    TextGraphics graphics;
    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        behaviourCheck= Mockito.mock(MenuController.class);
        menuMock=Mockito.mock(MainMenu.class);
        audiomock=Mockito.mock(AudioManager.class);
        InternalsCheck=new MenuController(menuMock,audiomock); // Passar para construtor para testar se o mock for chamado dentro dos metodos
        valuesCheck= new MenuController(new MainMenu(),new AudioManager("/audio/menu.wav"));
        gameMock=Mockito.mock(MainGame.class);
        gui=Mockito.mock(LanternaGUI.class);
        spyCheck=Mockito.spy(InternalsCheck);


    }

    @Test
    public void menuControllerTest(){
        Assertions.assertEquals(3,valuesCheck.getModel().getNumberEntries());
        Assertions.assertNotEquals(null,valuesCheck.getSound());
        Assertions.assertNotNull(InternalsCheck.getSound());
        Assertions.assertNotNull(valuesCheck.getModel());
        Assertions.assertNotNull(InternalsCheck.getModel());
    }
    @Test
    public void stepTestUp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        spyCheck.step(gameMock,GUI.ACTION.UP);
        Mockito.verify(spyCheck,Mockito.times(2)).getSound();
        Mockito.verify(menuMock,Mockito.times(1)).previousEntry();
        Mockito.verify(audiomock,Mockito.times(1)).restartAudio();
        Mockito.verify(audiomock,Mockito.times(1)).play();
    }
    @Test
    public void stepTestDown() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        spyCheck.step(gameMock,GUI.ACTION.DOWN);
        Mockito.verify(spyCheck,Mockito.times(2)).getSound();
        Mockito.verify(menuMock,Mockito.times(1)).nextEntry();
        Mockito.verify(audiomock,Mockito.times(1)).restartAudio();
        Mockito.verify(audiomock,Mockito.times(1)).play();
    }
    @Test
    public void stepTestSelect() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        spyCheck.step(gameMock,GUI.ACTION.SELECT);
        Mockito.verify(spyCheck,Mockito.times(2)).getSound();
        Mockito.verify(audiomock,Mockito.times(1)).restartAudio();
        Mockito.verify(audiomock,Mockito.times(1)).play();
        Mockito.reset(menuMock);
        Mockito.reset(spyCheck);
        Mockito.reset(audiomock);

        Mockito.when(menuMock.isSelectedSelect()).thenReturn(true);
        InternalsCheck.step(gameMock,GUI.ACTION.SELECT);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        Mockito.reset(gameMock);
        Mockito.reset(menuMock);

        Mockito.when(menuMock.isSelectedEditor()).thenReturn(true);
        InternalsCheck.step(gameMock,GUI.ACTION.SELECT);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        Mockito.reset(menuMock);
        Mockito.reset(gameMock);

        AudioManager audioMock2=Mockito.mock(AudioManager.class);
        Mockito.when(menuMock.isSelectedQuit()).thenReturn(true);
        Mockito.when(gameMock.getBGM()).thenReturn(audioMock2);
        spyCheck.step(gameMock,GUI.ACTION.SELECT);
        Mockito.verify(gameMock,Mockito.times(1)).getBGM();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        Mockito.verify(spyCheck,Mockito.times(3)).getSound();
        Mockito.verify(audiomock,Mockito.times(1)).close();
        Mockito.verify(audioMock2,Mockito.times(1)).close();

    }
    @Test
    public void stepTestMute() throws IOException {
        AudioManager audioMock2=Mockito.mock(AudioManager.class);

        Mockito.when(gameMock.getBGM()).thenReturn(audioMock2);
        Mockito.when(menuMock.isSelectedQuit()).thenReturn(true);
        InternalsCheck.step(gameMock,GUI.ACTION.SELECT);
        Mockito.verify(audiomock,Mockito.times(1)).close();
        Mockito.verify(audiomock,Mockito.times(1)).close();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        Mockito.reset(gameMock);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(true);
        InternalsCheck.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).resumeBGM();
        Mockito.reset(gameMock);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(false);
        InternalsCheck.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).muteBGM();
    }

}
