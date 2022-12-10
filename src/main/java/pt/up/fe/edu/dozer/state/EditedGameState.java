package pt.up.fe.edu.dozer.state;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.game.EditedArenaController;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EditedGameState extends GameState {
    public EditedGameState(EditorArena arena) {
        super(arena);
    }

    @Override
    protected Controller<Arena> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new EditedArenaController((EditorArena) getModel());
    }
}