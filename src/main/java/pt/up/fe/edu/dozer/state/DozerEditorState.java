package pt.up.fe.edu.dozer.state;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

public class DozerEditorState extends EditorState{
    public DozerEditorState(EditorArena arena) {
        super(arena);
    }

    @Override
    protected Controller<EditorArena> getController() {
        return null;
    }
}
