package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.ArenaBuilder;
import pt.up.fe.edu.dozer.model.game.arena.CopyArenaBuilder;
import pt.up.fe.edu.dozer.state.EditedGameState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EditedArenaController extends ArenaController {
    private final Arena baseArena;

    public EditedArenaController(Arena baseArena) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(baseArena, new AudioManager("/audio/monkeyApplause.wav"));
        ArenaBuilder builder = new CopyArenaBuilder(baseArena);
        this.baseArena = builder.createArena(new Arena());
    }

    @Override
    protected void restartArena(MainGame game) {
        game.resetTimer();
        game.setState(new EditedGameState(baseArena));
    }
}
