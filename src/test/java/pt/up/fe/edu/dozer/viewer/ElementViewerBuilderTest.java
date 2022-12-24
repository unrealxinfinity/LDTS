package pt.up.fe.edu.dozer.viewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.viewer.game.*;

public class ElementViewerBuilderTest {
    private ElementViewerBuilder builder;

    @BeforeEach
    public void setUp() {
        this.builder = new ElementViewerBuilder();
    }

    @Test
    public void getDozerViewer() {
        DozerViewer viewer = builder.getDozerViewer();

        Assertions.assertNotNull(viewer);
    }

    @Test
    public void getWallViewer() {
        WallViewer viewer = builder.getWallViewer();

        Assertions.assertNotNull(viewer);
    }

    @Test
    public void getBoulderViewer() {
        BoulderViewer viewer = builder.getBoulderViewer();
        ;

        Assertions.assertNotNull(viewer);
    }

    @Test
    public void getTargetViewer() {
        TargetViewer viewer = builder.getTargetViewer();

        Assertions.assertNotNull(viewer);
    }
}
