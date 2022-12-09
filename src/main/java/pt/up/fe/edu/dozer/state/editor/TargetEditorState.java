package pt.up.fe.edu.dozer.state.editor;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.editor.EditorArenaTargetController;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

public class TargetEditorState extends EditorState{
    public TargetEditorState(EditorArena arena) {
        super(arena);
    }

    @Override
    protected void setPlacingElem() {
        placingElem = "+";
    }

    @Override
    protected Controller<EditorArena> getController() {
        return new EditorArenaTargetController(getModel());
    }
}
