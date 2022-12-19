package pt.up.fe.edu.dozer.controller.MenuController;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
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
    MainGame gameMock;
    LanternaGUI guiMock;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        valueCheck=new LevelSelectController(new LevelSelect());
        menuMock= Mockito.mock(LevelSelect.class);
        internalCheck= new LevelSelectController(menuMock);
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
    public void stepTestUp() throws IOException {
        Assertions.assertEquals(0,internalCheck.getModel().getSelectedNum());
        internalCheck.step(gameMock, GUI.ACTION.UP);
        Mockito.verify(menuMock,Mockito.times(1)).previousEntry();
        Mockito.reset(menuMock);

        Mockito.when(menuMock.isSelectedTitle()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.UP);
        Mockito.verify(menuMock,Mockito.times(2)).previousEntry();

    }
    @Test
    public void stepTestDown() throws IOException {
        internalCheck.step(gameMock, GUI.ACTION.DOWN);
        Mockito.verify(menuMock,Mockito.times(1)).nextEntry();
        Mockito.reset(menuMock);

        Mockito.when(menuMock.isSelectedTitle()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.DOWN);
        Mockito.verify(menuMock,Mockito.times(2)).nextEntry();
    }
    @Test
    public void stepTestSelect() throws IOException {
        Mockito.when(menuMock.isSelectedStart()).thenReturn(true);
        Mockito.when(menuMock.getSelectedLevel()).thenReturn(1);
        internalCheck.step(gameMock, GUI.ACTION.SELECT);
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
    public void stepTestLeft() throws IOException {
        Mockito.when(menuMock.isSelectedNumber()).thenReturn(true);
        internalCheck.step(gameMock,GUI.ACTION.LEFT);
        Mockito.verify(menuMock,Mockito.times(1)).decrementCurrentDigit();
    }
    @Test
    public void stepTestRight() throws IOException {
        Mockito.when(menuMock.isSelectedNumber()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.RIGHT);
        Mockito.verify(menuMock,Mockito.times(1)).incrementCurrentDigit();
    }
    @Test
    public void stepTestMute() throws IOException {
        Mockito.when(gameMock.isBgmMuted()).thenReturn(true);
        internalCheck.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).resumeBGM();
        Mockito.reset(gameMock);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(false);
        internalCheck.step(gameMock, GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).muteBGM();
    }
}
