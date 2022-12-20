package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.ImportantWall;
import pt.up.fe.edu.dozer.model.game.elements.Wall;
import pt.up.fe.edu.dozer.state.editor.DozerEditorState;
import pt.up.fe.edu.dozer.state.editor.EditorState;

import java.util.List;

public class EditorArenaWallController extends EditorArenaController {
    public EditorArenaWallController(EditorArena arena) {
        super(arena);
    }

    @Override
    protected EditorState cycleState() {
        return new DozerEditorState(getModel());
    }

    @Override
    protected void placeElement() {
        Position position = getModel().getPlacer().getPosition();
        List<Wall> collisionWalls = getModel().getCollisionWalls();
        collisionWalls.add(new ImportantWall(position));
    }
}
