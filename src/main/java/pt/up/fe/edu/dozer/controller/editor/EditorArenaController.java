package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.editor.EditorState;

import java.io.IOException;

public abstract class EditorArenaController extends EditorController{
    private final PlacerController controller;

    public EditorArenaController(EditorArena arena) {
        super(arena);
        this.controller = new PlacerController(arena);
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.CYCLE) {
            game.setState(cycleState());
        }
        else if (action == GUI.ACTION.SELECT) {
            clearPosition();
            placeElement();
        }
        else this.controller.step(game, action, time);
    }

    protected abstract EditorState cycleState();
    protected abstract void placeElement();

    private void clearPosition() {
        EditorPositionClearer clearer = new EditorPositionClearer(getModel());
        clearer.clearPosition(getModel().getPlacer().getPosition());
    }
}
