package pt.up.fe.edu.dozer.state.editor;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.editor.EditorArenaBoulderController;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

public class BoulderEditorState extends EditorState{
    public BoulderEditorState(EditorArena arena) {
        super(arena,"&");
    }

    @Override
    protected Controller<EditorArena> getController() {
        return new EditorArenaBoulderController(getModel());
    }
}
