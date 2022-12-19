package pt.up.fe.edu.dozer.controller.game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EditedArenaTest {
    @Test
    public void restartTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        EditorArena arena = new EditorArena(20, 20);
        arena.setDozer(new Dozer(5,7));
        EditedArenaController controller = new EditedArenaController(arena);
        MainGame game = Mockito.mock(MainGame.class);

        controller.restartArena(game);

        Mockito.verify(game,Mockito.times(1)).resetTimer();
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any());
    }
}
