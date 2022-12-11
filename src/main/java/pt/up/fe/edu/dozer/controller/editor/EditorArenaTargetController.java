package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Target;
import pt.up.fe.edu.dozer.state.editor.EditorState;
import pt.up.fe.edu.dozer.state.editor.WallEditorState;

public class EditorArenaTargetController extends EditorArenaController {
    public EditorArenaTargetController(EditorArena arena) {
        super(arena);
    }

    @Override
    protected EditorState cycleState() {
        return new WallEditorState(getModel());
    }

    @Override
    protected void placeElement() {
        getModel().getTargets().add(new Target(getModel().getPlacer().getPosition()));
    }
}
