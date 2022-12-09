package pt.up.fe.edu.dozer.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

import java.io.IOException;

public class GameStateTest {
    @Test
    public void getModel() {
        GameState state = new GameState(new Arena(20, 20));

        Assertions.assertNotNull(state.getModel());
    }

    @Test
    public void getViewer() {
        GameState state = new GameState(new Arena(20,20));

        Assertions.assertNotNull(state.getViewer());
    }

    @Test
    public void getController() {
        GameState state = new GameState(new Arena(20,20));

        Assertions.assertNotNull(state.getController());
    }

    @Test
    public void stepTest() throws IOException {
        GUI gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.LEFT);
        GameState state = new GameState(new Arena(20,20));

        state.step(null, gui, 0);

        Mockito.verify(gui, Mockito.times(1)).getNextAction();
    }
}
