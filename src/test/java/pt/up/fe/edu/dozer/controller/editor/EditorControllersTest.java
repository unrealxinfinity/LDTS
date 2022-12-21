package pt.up.fe.edu.dozer.controller.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.model.game.elements.Placer;

import java.io.IOException;

public class EditorControllersTest {

    @Test
    public void boulderCycle() {
        EditorArenaBoulderController controller = new EditorArenaBoulderController(new EditorArena(20,20));

        Assertions.assertNotNull(controller.cycleState());
    }

    @Test
    public void dozerCycle() {
        EditorArenaDozerController controller = new EditorArenaDozerController(new EditorArena(20,20));

        Assertions.assertNotNull(controller.cycleState());
    }

    @Test
    public void wallCycle() {
        EditorArenaWallController controller = new EditorArenaWallController(new EditorArena(20,20));

        Assertions.assertNotNull(controller.cycleState());
    }

    @Test
    public void targetCycle() {
        EditorArenaTargetController controller = new EditorArenaTargetController(new EditorArena(20,20));

        Assertions.assertNotNull(controller.cycleState());
    }

    @Test
    public void stepCycle() throws IOException {
        EditorArena arena = Mockito.mock(EditorArena.class);
        MainGame game = Mockito.mock(MainGame.class);
        EditorArenaDozerController controller = new EditorArenaDozerController(arena);

        controller.step(game, GUI.ACTION.CYCLE);

        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void stepSelect() throws IOException {
        EditorArena arena = Mockito.mock(EditorArena.class);
        MainGame game = Mockito.mock(MainGame.class);
        Placer placer = Mockito.mock(Placer.class);
        Mockito.when(arena.getPlacer()).thenReturn(placer);
        EditorArenaDozerController controller = new EditorArenaDozerController(arena);

        controller.step(game, GUI.ACTION.SELECT);

        Mockito.verify(arena, Mockito.times(2)).getPlacer();
        Mockito.verify(placer, Mockito.times(2)).getPosition();
    }

    @Test
    public void stepRemove() throws IOException {
        EditorArena arena = Mockito.mock(EditorArena.class);
        MainGame game = Mockito.mock(MainGame.class);
        Placer placer = Mockito.mock(Placer.class);
        Mockito.when(arena.getPlacer()).thenReturn(placer);
        EditorArenaDozerController controller = new EditorArenaDozerController(arena);

        controller.step(game, GUI.ACTION.REMOVE);

        Mockito.verify(arena, Mockito.times(1)).getPlacer();
        Mockito.verify(placer, Mockito.times(1)).getPosition();
    }
    @Test
    public void stepTestRestart() throws IOException {
        EditorArena arenaMock= Mockito.mock(EditorArena.class);
        MainGame gameMock=Mockito.mock(MainGame.class);
        Placer placerMock=Mockito.mock(Placer.class);

        Mockito.when(arenaMock.getPlacer()).thenReturn(placerMock);
        EditorArenaDozerController controllerMock=Mockito.spy(new EditorArenaDozerController(arenaMock));

        controllerMock.step(gameMock,GUI.ACTION.RESTART);
        Mockito.verify(gameMock,Mockito.atLeastOnce()).setState(Mockito.any());

    }
    @Test
    public void stepTestPause() throws IOException {
        EditorArena arenaMock= Mockito.mock(EditorArena.class);
        MainGame gameMock=Mockito.mock(MainGame.class);
        Placer placerMock=Mockito.mock(Placer.class);

        Mockito.when(arenaMock.getPlacer()).thenReturn(placerMock);
        EditorArenaDozerController controllerMock=Mockito.spy(new EditorArenaDozerController(arenaMock));

        controllerMock.step(gameMock,GUI.ACTION.PAUSE);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
    }
    @Test
    public void stepTestSave() throws IOException {
        EditorArena arenaMock= Mockito.mock(EditorArena.class);
        MainGame gameMock=Mockito.mock(MainGame.class);
        Placer placerMock=Mockito.mock(Placer.class);
        Dozer dozerMock=Mockito.mock(Dozer.class);

        Mockito.when(arenaMock.getPlacer()).thenReturn(placerMock);
        EditorArenaDozerController controllerMock=Mockito.spy(new EditorArenaDozerController(arenaMock));
        Mockito.when(arenaMock.getDozer()).thenReturn(dozerMock);

        controllerMock.step(gameMock,GUI.ACTION.SAVE);
        Mockito.verify(gameMock,Mockito.times(1)).resetTimer();
        Mockito.verify(gameMock,Mockito.times(1)).setState(Mockito.any());
    }
    @Test
    public void stepMuteTest() throws IOException {
        EditorArena arenaMock= Mockito.mock(EditorArena.class);
        MainGame gameMock=Mockito.mock(MainGame.class);
        Placer placerMock=Mockito.mock(Placer.class);

        Mockito.when(arenaMock.getPlacer()).thenReturn(placerMock);
        EditorArenaDozerController controllerMock=Mockito.spy(new EditorArenaDozerController(arenaMock));

        Mockito.when(gameMock.isBgmMuted()).thenReturn(true);
        controllerMock.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).resumeBGM();
        Mockito.reset(gameMock);

        Mockito.when(gameMock.isBgmMuted()).thenReturn(false);
        controllerMock.step(gameMock,GUI.ACTION.MUTE);
        Mockito.verify(gameMock,Mockito.times(1)).muteBGM();
        Mockito.reset(gameMock);

    }
}
