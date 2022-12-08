package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.state.BoulderEditorState;
import pt.up.fe.edu.dozer.state.EditorState;

public class EditorArenaDozerController extends EditorArenaController{
    public EditorArenaDozerController(EditorArena arena) {
        super(arena);
    }

    @Override
    protected EditorState cycleState() {
        return new BoulderEditorState(getModel());
    }

    @Override
    protected void placeElement() {
        getModel().setDozer(new Dozer(getModel().getPlacer().getPosition()));
    }
}
