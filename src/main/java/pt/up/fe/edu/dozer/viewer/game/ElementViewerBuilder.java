package pt.up.fe.edu.dozer.viewer.game;

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
