package pt.up.fe.edu.dozer.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.model.game.elements.Target;

import java.util.Arrays;

public class TargetControllerTest {
    @Test
    public void update() {
        Arena arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getTargets()).thenReturn(Arrays.asList(new Target(1, 2), new Target(3, 4), new Target(5, 6)));
        Mockito.when(arena.getBoulders()).thenReturn(Arrays.asList(new Boulder(4, 4), new Boulder(1, 1), new Boulder(3, 4)));
        TargetController controller = new TargetController(arena);

        controller.update();

        Assertions.assertEquals(1, controller.getBouldersInTargets());
    }
}
