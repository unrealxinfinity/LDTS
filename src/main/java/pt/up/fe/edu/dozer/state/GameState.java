package pt.up.fe.edu.dozer.state;

import pt.up.fe.edu.dozer.controller.Controller;

import pt.up.fe.edu.dozer.controller.gameController.ArenaController;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.game.ElementViewerBuilder;
import pt.up.fe.edu.dozer.viewer.game.GameViewer;

public class GameState extends State<Arena>{
    public GameState(Arena arena){super(arena);}
    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel(),new ElementViewerBuilder());
    }

    @Override
    protected Controller<Arena> getController() {

        return new ArenaController(getModel());

    }

}
