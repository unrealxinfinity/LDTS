package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

public abstract class EditorController extends Controller<EditorArena> {
    public EditorController(EditorArena arena) {
        super(arena);
    }
}
