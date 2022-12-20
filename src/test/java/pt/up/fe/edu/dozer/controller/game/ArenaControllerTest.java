package pt.up.fe.edu.dozer.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Target;

import javax.crypto.spec.PSource;
import javax.management.remote.JMXAddressable;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaControllerTest {
   ArenaController internalCheck;
    ArenaController behaviourCheck;
    ArenaController spyCheck;
    Arena arenaMock;
    MainGame gameMock;
    AudioManager audioMock;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        arenaMock= Mockito.mock(Arena.class);
        audioMock= Mockito.mock(AudioManager.class);
        internalCheck=new ArenaController(arenaMock,audioMock);
        gameMock=Mockito.mock(MainGame.class);
        behaviourCheck=Mockito.mock(ArenaController.class);
        spyCheck=Mockito.spy(internalCheck);
    }
    @Test
    public void arenaControllerTest(){
        Assertions.assertNotNull(internalCheck.getSound());
        Assertions.assertNotNull(internalCheck.getModel());
        Assertions.assertNotNull(internalCheck.getBoulderController());
        Assertions.assertNotNull(internalCheck.getTargetController());
        Assertions.assertNotNull(internalCheck.getDozerController());

    }
    @Test
    public void stepTestPause() throws IOException {
        internalCheck.step(gameMock, GUI.ACTION.PAUSE);
        Mockito.verify(audioMock,Mockito.times(1)).close();
        Mockito.verify(gameMock, Mockito.times(1)).setState(Mockito.any());
    }
    @Test
    public void stepTestRestart() throws IOException {
        Mockito.doThrow(new NullPointerException()).when(spyCheck).restartArena(Mockito.any());
        try{
            spyCheck.step(gameMock,GUI.ACTION.RESTART);
        }
        catch (NullPointerException ignored){
        }
        Mockito.verify(spyCheck,Mockito.times(1)).restartArena(Mockito.any());

    }
    @Test
    public void stepTestNextLevel() throws IOException {

        //Assertions.assertEquals(0,arenaMock.getTargets().size());
        internalCheck.step(gameMock,null);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        Mockito.verify(audioMock,Mockito.times(1)).play();
        Mockito.reset(arenaMock);
        Mockito.reset(gameMock);

        Mockito.when(arenaMock.getLevelNum()).thenReturn(9999);
        internalCheck.step(gameMock,null);
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
    }
    @Test
    public void stepTestMute() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        List<Target> stubArray= new ArrayList<Target>();
        stubArray.add(new Target(1,2));
        Mockito.when(arenaMock.getTargets()).thenReturn(stubArray);
        internalCheck=new ArenaController(arenaMock,audioMock);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(true);
        internalCheck.step(gameMock, GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).resumeBGM();
        Mockito.when(gameMock.isBgmMuted()).thenReturn(false);
        internalCheck.step(gameMock, GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).muteBGM();
    }
    @Test
    public void restartArenaTest() throws IOException {
        Mockito.when(arenaMock.getLevelNum()).thenReturn(1);
        internalCheck.restartArena(gameMock);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
    }
}
