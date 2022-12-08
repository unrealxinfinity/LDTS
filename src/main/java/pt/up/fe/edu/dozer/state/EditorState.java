package pt.up.fe.edu.dozer.state;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.gameController.ArenaController;
import pt.up.fe.edu.dozer.controller.gameController.EditorArenaController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.game.EditorViewer;
import pt.up.fe.edu.dozer.viewer.game.ElementViewerBuilder;
import pt.up.fe.edu.dozer.viewer.game.GameViewer;

import java.io.IOException;

public class EditorState extends State<EditorArena>{
    public EditorState(EditorArena arena) {
        super(arena);
    }

    @Override
    protected Viewer<EditorArena> getViewer() {
        return new EditorViewer(getModel());
    }

    @Override
    protected Controller<EditorArena> getController() {
        return new EditorArenaController(getModel());
    }
}
