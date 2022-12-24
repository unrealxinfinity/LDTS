package pt.up.fe.edu.dozer.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EditedGameStateTest {
    @Test
    public void getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        EditorArena arena = new EditorArena(20, 20);
        arena.setDozer(new Dozer(2,2));
        EditedGameState state = new EditedGameState(arena);

        Assertions.assertNotNull(state.getController());
    }
}
