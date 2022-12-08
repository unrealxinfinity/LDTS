package pt.up.fe.edu.dozer.state.editor;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.game.EditorViewer;

public abstract class EditorState extends State<EditorArena> {
    public EditorState(EditorArena arena) {
        super(arena);
    }

    @Override
    protected Viewer<EditorArena> getViewer() {
        return new EditorViewer(getModel());
    }

    @Override
    protected abstract Controller<EditorArena> getController();
}
