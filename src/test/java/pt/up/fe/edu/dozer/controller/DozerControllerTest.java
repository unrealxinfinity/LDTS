package pt.up.fe.edu.dozer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.controller.gameController.DozerController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;

import java.io.IOException;

public class DozerControllerTest {
    private Arena arena;
    private Dozer dozer;
    private Position position;

    @BeforeEach
    public void setUp() {
        arena = Mockito.mock(Arena.class);
        dozer = Mockito.mock(Dozer.class);
        position = Mockito.mock(Position.class);
        Mockito.when(arena.getDozer()).thenReturn(dozer);
        Mockito.when(dozer.getPosition()).thenReturn(position);
    }

    @Test
    public void stepTestLeft() {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.LEFT, 0);

        Mockito.verify(position, Mockito.times(1)).moveLeft();
    }

    @Test
    public void stepTestRight() {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.RIGHT, 0);

        Mockito.verify(position, Mockito.times(1)).moveRight();
    }

    @Test
    public void stepTestDown() {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.DOWN, 0);

        Mockito.verify(position, Mockito.times(1)).moveDown();
    }

    @Test
    public void stepTestUp() {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.UP, 0);

        Mockito.verify(position, Mockito.times(1)).moveUp();
    }
}
