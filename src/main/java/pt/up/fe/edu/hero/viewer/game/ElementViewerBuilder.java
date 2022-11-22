package pt.up.fe.edu.hero.viewer.game;

import pt.up.fe.edu.hero.model.game.elements.Dozer;

public class ElementViewerBuilder {
    public WallViewer getWallViewer() {
        return new WallViewer();
    }

    public DozerViewer getDozerViewer() {
        return new DozerViewer();
    }

    public TargetViewer getTargetViewer() {
        return new TargetViewer();
    }

    public BoulderViewer getBoulderViewer() {
        return new BoulderViewer();
    }
}
