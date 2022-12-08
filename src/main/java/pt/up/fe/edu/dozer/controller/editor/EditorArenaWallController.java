package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.ImportantWall;
import pt.up.fe.edu.dozer.state.editor.BoulderEditorState;
import pt.up.fe.edu.dozer.state.editor.EditorState;

public class EditorArenaWallController extends EditorArenaController{
    public EditorArenaWallController(EditorArena arena) {
        super(arena);
    }

    @Override
    protected EditorState cycleState() {
        return new BoulderEditorState(getModel());
    }

    @Override
    protected void placeElement() {
        getModel().getCollisionWalls().add(new ImportantWall(getModel().getPlacer().getPosition()));
    }
}