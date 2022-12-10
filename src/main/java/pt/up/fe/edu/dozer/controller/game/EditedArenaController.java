package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.GameState;

import java.io.IOException;

public class EditedArenaController extends ArenaController{
    private EditorArena baseArena;
    public EditedArenaController(EditorArena baseArena) {
        super(baseArena.getArena());
        this.baseArena = baseArena;
    }

    @Override
    protected void restartArena(MainGame game) {
        game.resetTimer();
        game.setState(new GameState(baseArena.getArena()));
    }
}
