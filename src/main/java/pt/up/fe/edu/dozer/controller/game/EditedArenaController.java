package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.EditedGameState;
import pt.up.fe.edu.dozer.state.GameState;

import java.io.IOException;

public class EditedArenaController extends ArenaController{
    private final EditorArena baseArena;
    public EditedArenaController(EditorArena baseArena, Arena model) {
        super(model);
        this.baseArena = baseArena;
    }

    @Override
    protected void restartArena(MainGame game) {
        game.resetTimer();
        game.setState(new EditedGameState(baseArena));
    }
}
