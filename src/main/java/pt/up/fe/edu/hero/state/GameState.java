package pt.up.fe.edu.hero.state;

import pt.up.fe.edu.hero.controller.Controller;
import pt.up.fe.edu.hero.controller.gameController.GameController;
import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.arena.Arena;
import pt.up.fe.edu.hero.viewer.Viewer;
import pt.up.fe.edu.hero.viewer.game.ElementViewerBuilder;
import pt.up.fe.edu.hero.viewer.game.GameViewer;

public class GameState extends State<Arena>{
    public GameState(Arena arena){super(arena);}
    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel(),new ElementViewerBuilder());
    }

    @Override
    protected Controller<Arena> getController() {
        return null;
    }

}
