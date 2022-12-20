package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Target;
import pt.up.fe.edu.dozer.state.editor.EditorState;
import pt.up.fe.edu.dozer.state.editor.WallEditorState;

import java.util.List;

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
        Position position = getModel().getPlacer().getPosition();
        List<Target> targets = getModel().getTargets();
        targets.add(new Target(position));
    }
}
