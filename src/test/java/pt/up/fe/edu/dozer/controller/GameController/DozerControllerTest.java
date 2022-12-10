package pt.up.fe.edu.dozer.controller.GameController;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.controller.game.DozerController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    public void stepTestLeft() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.LEFT, 0);

        Mockito.verify(position, Mockito.times(1)).moveLeft();
    }

    @Test
    public void stepTestRight() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.RIGHT, 0);

        Mockito.verify(position, Mockito.times(1)).moveRight();
    }

    @Test
    public void stepTestDown() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.DOWN, 0);

        Mockito.verify(position, Mockito.times(1)).moveDown();
    }

    @Test
    public void stepTestUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.UP, 0);

        Mockito.verify(position, Mockito.times(1)).moveUp();
    }
}
