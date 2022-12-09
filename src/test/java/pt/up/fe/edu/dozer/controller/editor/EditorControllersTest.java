package pt.up.fe.edu.dozer.controller.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
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

        controller.step(game, GUI.ACTION.CYCLE, 0);

        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any());
    }

    @Test
    public void stepSelect() throws IOException {
        EditorArena arena = Mockito.mock(EditorArena.class);
        MainGame game = Mockito.mock(MainGame.class);
        Placer placer = Mockito.mock(Placer.class);
        Mockito.when(arena.getPlacer()).thenReturn(placer);
        EditorArenaDozerController controller = new EditorArenaDozerController(arena);

        controller.step(game, GUI.ACTION.SELECT, 0);

        Mockito.verify(arena, Mockito.times(2)).getPlacer();
        Mockito.verify(placer, Mockito.times(2)).getPosition();
    }
}
