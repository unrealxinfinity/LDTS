package pt.up.fe.edu.dozer.controller.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

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
}
