package pt.up.fe.edu.dozer.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Target;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaControllerTest {
   ArenaController internalCheck;
    ArenaController behaviourCheck;
    Arena arenaMock;
    MainGame gameMock;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        arenaMock= Mockito.mock(Arena.class);
        internalCheck=new ArenaController(arenaMock);
        gameMock=Mockito.mock(MainGame.class);
        behaviourCheck=Mockito.mock(ArenaController.class);
    }
    @Test
    public void ArenaControllerTest(){
        Assertions.assertNotNull(internalCheck.getSound());
        Assertions.assertNotNull(internalCheck.getModel());
        Assertions.assertNotNull(internalCheck.getBoulderController());
        Assertions.assertNotNull(internalCheck.getTargetController());
        Assertions.assertNotNull(internalCheck.getDozerController());

    }
    @Test
    public void stepTestPause() throws IOException {
        internalCheck.step(gameMock, GUI.ACTION.PAUSE);
        Mockito.verify(gameMock, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void stepTestNextLevel() throws IOException {

        Assertions.assertEquals(0,arenaMock.getTargets().size());
        internalCheck.step(gameMock,null);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
        Mockito.reset(arenaMock);
    }
    @Test
    public void stepTestMute() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        List<Target> stubArray= new ArrayList<Target>();
        stubArray.add(new Target(1,2));
        Mockito.when(arenaMock.getTargets()).thenReturn(stubArray);
        internalCheck=new ArenaController(arenaMock);

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
