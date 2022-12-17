package pt.up.fe.edu.dozer.controller.editor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Placer;

import java.io.IOException;

public class PlacerControllerTest {
    private EditorArena arena;
    private PlacerController controller;
    private Placer placer;

    @BeforeEach
    public void setUp() {
        arena = Mockito.mock(EditorArena.class);
        Mockito.when(arena.getWidth()).thenReturn(15);
        Mockito.when(arena.getHeight()).thenReturn(50);
        placer = Mockito.mock(Placer.class);
        Mockito.when(placer.getPosition()).thenReturn(new Position(14, 0));
        Mockito.when(arena.getPlacer()).thenReturn(placer);
        controller = new PlacerController(arena);
    }

    @Test
    public void moveUp() throws IOException {
        controller.step(null, GUI.ACTION.UP);

        Mockito.verify(placer, Mockito.times(0)).setPosition(Mockito.any());
    }

    @Test
    public void moveLeft() throws IOException {
        controller.step(null, GUI.ACTION.LEFT);

        Mockito.verify(placer, Mockito.times(1)).setPosition(Mockito.eq(new Position(13, 0)));
    }

    @Test
    public void moveRight() throws IOException {
        controller.step(null, GUI.ACTION.RIGHT);

        Mockito.verify(placer, Mockito.times(0)).setPosition(Mockito.any());
    }

    @Test
    public void moveDown() throws IOException {
        controller.step(null, GUI.ACTION.DOWN);

        Mockito.verify(placer, Mockito.times(1)).setPosition(Mockito.eq(new Position(14, 1)));
    }
}
