package pt.up.fe.edu.dozer.controller.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.LevelReader;
import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;

import java.io.IOException;

public class BoulderControllerTest {
    BoulderController behaviourCheck;
    BoulderController valueCheck;
    BoulderController internalCheck;

    @BeforeEach
    public void setUp(){
        behaviourCheck= Mockito.mock(BoulderController.class);
        internalCheck=new BoulderController(Mockito.mock(Arena.class),Mockito.mock(TargetController.class));
    }
    @Test
    public void moveUp() throws IOException {
        TargetController controller = Mockito.mock(TargetController.class);
        BoulderController boulderController = new BoulderController(new LoaderArenaBuilder(1, new LevelReader()).createArena(new Arena()), controller);

        boolean actual = boulderController.moveBoulderUp(new Position(12,5));

        Mockito.verify(controller, Mockito.times(0)).update();
        Assertions.assertFalse(actual);
    }

    @Test
    public void moveLeft() throws IOException {
        TargetController controller = Mockito.mock(TargetController.class);
        BoulderController boulderController = new BoulderController(new LoaderArenaBuilder(1, new LevelReader()).createArena(new Arena()), controller);

        boolean actual = boulderController.moveBoulderLeft(new Position(12,5));

        Mockito.verify(controller, Mockito.times(1)).update();
        Assertions.assertTrue(actual);
    }

    @Test
    public void moveRight() throws IOException {
        TargetController controller = Mockito.mock(TargetController.class);
        BoulderController boulderController = new BoulderController(new LoaderArenaBuilder(1, new LevelReader()).createArena(new Arena()), controller);

        boolean actual = boulderController.moveBoulderRight(new Position(12,5));

        Mockito.verify(controller, Mockito.times(1)).update();
        Assertions.assertTrue(actual);
    }

    @Test
    public void moveDown() throws IOException {
        TargetController controller = Mockito.mock(TargetController.class);
        BoulderController boulderController = new BoulderController(new LoaderArenaBuilder(1, new LevelReader()).createArena(new Arena()), controller);

        boolean actual = boulderController.moveBoulderDown(new Position(12,5));

        Mockito.verify(controller, Mockito.times(0)).update();
        Assertions.assertFalse(actual);
    }
}
