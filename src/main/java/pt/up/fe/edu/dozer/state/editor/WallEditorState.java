package pt.up.fe.edu.dozer.state.editor;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.editor.EditorArenaWallController;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

public class WallEditorState extends EditorState{
    public WallEditorState(EditorArena arena) {
        super(arena,"#");
    }

    @Override
    protected Controller<EditorArena> getController() {
        return new EditorArenaWallController(getModel());
    }
}
