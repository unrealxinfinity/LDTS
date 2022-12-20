package pt.up.fe.edu.dozer.controller.MenuController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.controller.menu.LevelSelectController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class LevelSelectControllerTest {
    LevelSelectController valueCheck;
    LevelSelectController internalCheck;
    LevelSelect menuMock;
    AudioManager audioMock;
    MainGame gameMock;
    LanternaGUI guiMock;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        valueCheck=new LevelSelectController(new LevelSelect(),new AudioManager("/audio/menu.wav"));
        menuMock= Mockito.mock(LevelSelect.class);
        audioMock=Mockito.mock(AudioManager.class);
        internalCheck= new LevelSelectController(menuMock,audioMock);
        gameMock=Mockito.mock(MainGame.class);
        guiMock= Mockito.mock(LanternaGUI.class);
    }

    @Test
    public void LevelSelectControllerTest(){
        Assertions.assertNotNull(internalCheck.getSound());
        Assertions.assertNotNull(valueCheck.getSound());
        Assertions.assertNotNull(internalCheck.getModel());
        Assertions.assertNotNull(valueCheck.getModel());
    }

    @Test
    public void stepTestUp() throws IOException, UnsupportedAudioFileException, LineUnavailableException {


        Assertions.assertEquals(0,internalCheck.getModel().getSelectedNum());
        internalCheck.step(gameMock, GUI.ACTION.UP);
        Mockito.verify(audioMock,Mockito.times(1)).restartAudio();
        Mockito.verify(audioMock,Mockito.times(1)).play();
        Mockito.verify(menuMock,Mockito.times(1)).previousEntry();
        Mockito.reset(menuMock);

        Mockito.when(menuMock.isSelectedTitle()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.UP);
        Mockito.verify(menuMock,Mockito.times(1)).previousEntry();

    }
    @Test
    public void stepTestDown() throws IOException, UnsupportedAudioFileException, LineUnavailableException {


        internalCheck.step(gameMock, GUI.ACTION.DOWN);
        Mockito.verify(audioMock,Mockito.times(1)).restartAudio();
        Mockito.verify(audioMock,Mockito.times(1)).play();
        Mockito.verify(menuMock,Mockito.times(1)).nextEntry();
        Mockito.reset(menuMock);

        Mockito.when(menuMock.isSelectedTitle()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.DOWN);
        Mockito.verify(menuMock,Mockito.times(1)).nextEntry();
    }
    @Test
    public void stepTestSelect() throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        Mockito.when(menuMock.isSelectedStart()).thenReturn(true);
        Mockito.when(menuMock.getSelectedLevel()).thenReturn(1);
        internalCheck.step(gameMock, GUI.ACTION.SELECT);
        Mockito.verify(audioMock,Mockito.times(1)).restartAudio();
        Mockito.verify(audioMock,Mockito.times(1)).play();
        Mockito.verify(gameMock, Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        Mockito.reset(gameMock);
        Mockito.reset(menuMock);

        Mockito.when(menuMock.isSelectedBack()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.SELECT);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
    }
    @Test
    public void stepTestLeft() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        Mockito.when(menuMock.isSelectedNumber()).thenReturn(true);
        internalCheck.step(gameMock,GUI.ACTION.LEFT);
        Mockito.verify(audioMock,Mockito.times(1)).restartAudio();
        Mockito.verify(audioMock,Mockito.times(1)).play();
        Mockito.verify(menuMock,Mockito.times(1)).decrementCurrentDigit();
    }
    @Test
    public void stepTestRight() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        Mockito.when(menuMock.isSelectedNumber()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.RIGHT);
        Mockito.verify(audioMock,Mockito.times(1)).restartAudio();
        Mockito.verify(audioMock,Mockito.times(1)).play();
        Mockito.verify(menuMock,Mockito.times(1)).incrementCurrentDigit();
    }
    @Test
    public void stepTestMute() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Mockito.when(gameMock.isBgmMuted()).thenReturn(true);
        internalCheck.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).resumeBGM();
        Mockito.reset(gameMock);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(false);
        internalCheck.step(gameMock, GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).muteBGM();
    }
}
