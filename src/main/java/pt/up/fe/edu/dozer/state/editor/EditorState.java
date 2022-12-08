package pt.up.fe.edu.dozer.state.editor;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.game.EditorViewer;
import pt.up.fe.edu.dozer.viewer.game.ElementViewerBuilder;
import pt.up.fe.edu.dozer.viewer.game.GameViewer;
import pt.up.fe.edu.dozer.viewer.game.PlacerViewerBuilder;

public abstract class EditorState extends State<EditorArena> {
    public EditorState(EditorArena arena) {
        super(arena);
    }

    @Override
    protected Viewer<EditorArena> getViewer() {
        return new EditorViewer(getModel(), new PlacerViewerBuilder(), new GameViewer(getModel(), new ElementViewerBuilder()));
    }

    @Override
    protected abstract Controller<EditorArena> getController();
}
