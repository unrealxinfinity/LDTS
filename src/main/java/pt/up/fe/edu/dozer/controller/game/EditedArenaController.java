package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.EditedGameState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EditedArenaController extends ArenaController {
    private final EditorArena baseArena;

    public EditedArenaController(EditorArena baseArena) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(baseArena);
        this.baseArena = baseArena.getArena();
    }

    @Override
    protected void restartArena(MainGame game) {
        game.resetTimer();
        game.setState(new EditedGameState(baseArena));
    }
}
