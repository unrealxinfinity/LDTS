package pt.up.fe.edu.dozer.controller.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    private BoulderController boulderMock;
    private Position position;
    private DozerController spyMock;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        arena = Mockito.mock(Arena.class);
        dozer = Mockito.mock(Dozer.class);
        boulderMock=Mockito.mock(BoulderController.class);
        position = Mockito.mock(Position.class);
        Mockito.when(arena.getDozer()).thenReturn(dozer);
        Mockito.when(dozer.getPosition()).thenReturn(position);

        spyMock= Mockito.spy(new DozerController(arena,boulderMock));
    }

    @Test
    public void stepTestLeft() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.LEFT);

        Mockito.verify(position, Mockito.times(1)).moveLeft();


    }

    @Test
    public void stepTestRight() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.RIGHT);

        Mockito.verify(position, Mockito.times(1)).moveRight();
    }

    @Test
    public void stepTestDown() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.DOWN);

        Mockito.verify(position, Mockito.times(1)).moveDown();
    }

    @Test
    public void stepTestUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        DozerController dozerController = new DozerController(arena, null);

        dozerController.step(null, GUI.ACTION.UP);

        Mockito.verify(position, Mockito.times(1)).moveUp();
    }
    @Test
    public void moveDozerTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        spyMock.moveDozerLeft();

    }
}
