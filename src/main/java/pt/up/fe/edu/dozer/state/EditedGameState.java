package pt.up.fe.edu.dozer.state;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.game.EditedArenaController;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.game.ElementViewerBuilder;
import pt.up.fe.edu.dozer.viewer.game.GameViewer;

public class EditedGameState extends GameState{
    private EditorArena baseArena;
    public EditedGameState(EditorArena arena) {
        super(arena.getArena());
        this.baseArena = arena;
    }

    @Override
    protected Controller<Arena> getController() {
        return new EditedArenaController(baseArena, getModel());
    }
}