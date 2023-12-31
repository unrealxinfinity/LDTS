package pt.up.fe.edu.dozer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainGameTest {
    MainGame internalCheck;
    MainGame spyCheck;
    AudioManager audioMock;
    State stateMock;
    LanternaGUI guiMock;


    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException, URISyntaxException, FontFormatException {
        audioMock = Mockito.mock(AudioManager.class);
        stateMock = Mockito.mock(MenuState.class);
        spyCheck = Mockito.spy(new MainGame(stateMock, audioMock));
        guiMock = Mockito.mock(LanternaGUI.class);

    }

    @Test
    public void mainGameTest() {
        Assertions.assertNotNull(spyCheck.getBGM());
    }

    @Test
    public void resetTimer() {
        spyCheck.resetTimer();
        Assertions.assertEquals((float) System.currentTimeMillis(), (float) spyCheck.getInitialTime(), 0.001);
    }

    @Test
    public void muteBgmTest() {
        spyCheck.muteBGM();
        Mockito.verify(audioMock, Mockito.times(1)).mute();
    }

    @Test
    public void resumeBgmTest() {
        spyCheck.resumeBGM();
        Mockito.verify(audioMock, Mockito.times(1)).loopSound();
    }

    @Test
    public void isBgmMutedTest() {
        spyCheck.muteBGM();
        Assertions.assertTrue(spyCheck.isBgmMuted());

        spyCheck.resumeBGM();
        Assertions.assertFalse(spyCheck.isBgmMuted());
    }

    @Test
    public void getBgmTest() {
        Assertions.assertEquals(audioMock, spyCheck.getBGM());
    }
}