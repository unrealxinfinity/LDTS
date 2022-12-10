package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.state.editor.EditorState;
import pt.up.fe.edu.dozer.state.editor.TargetEditorState;

public class EditorArenaBoulderController extends EditorArenaController {
    public EditorArenaBoulderController(EditorArena arena) {
        super(arena);
    }

    @Override
    protected EditorState cycleState() {
        return new TargetEditorState(getModel());
    }

    @Override
    protected void placeElement() {
        getModel().getBoulders().add(new Boulder(getModel().getPlacer().getPosition()));
    }
}
