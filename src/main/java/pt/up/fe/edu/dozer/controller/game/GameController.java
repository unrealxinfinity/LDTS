package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }

    public GameController(Arena arena, AudioManager audio) {
        super(arena, audio);
    }

}
