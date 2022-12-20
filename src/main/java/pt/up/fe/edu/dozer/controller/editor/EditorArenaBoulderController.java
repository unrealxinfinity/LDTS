package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.state.editor.EditorState;
import pt.up.fe.edu.dozer.state.editor.TargetEditorState;

import java.util.List;

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
        Position position = getModel().getPlacer().getPosition();
        List<Boulder> boulders = getModel().getBoulders();
        boulders.add(new Boulder(position));
    }
}
