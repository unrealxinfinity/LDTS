package pt.up.fe.edu.hero.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.arena.Arena;
import pt.up.fe.edu.hero.model.game.elements.*;
import pt.up.fe.edu.hero.viewer.game.*;

import java.util.Arrays;

public class GameViewerTest {
    private GUI gui;

    @BeforeEach
    public void setUp() {
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawElementsTest() {
        Arena arena = new Arena(20,20);
        arena.setDozer(new Dozer(5,5));
        arena.setBoulders(Arrays.asList(new Boulder(3,4), new Boulder(7,7)));
        arena.setTargets(Arrays.asList(new Target(7,7), new Target(8,9)));
        arena.setWalls(Arrays.asList(new Wall(1,1), new Wall(2,2), new Wall(10,10)));
        arena.setCollisionWalls(Arrays.asList(new ImportantWall(3,3), new ImportantWall(11,11), new ImportantWall(13,14)));

        ElementViewerBuilder builder = Mockito.mock(ElementViewerBuilder.class);
        WallViewer wallViewer = Mockito.mock(WallViewer.class);
        DozerViewer dozerViewer = Mockito.mock(DozerViewer.class);
        TargetViewer targetViewer = Mockito.mock(TargetViewer.class);
        BoulderViewer boulderViewer = Mockito.mock(BoulderViewer.class);

        GameViewer viewer = new GameViewer(arena, builder);
        Mockito.when(builder.getBoulderViewer()).thenReturn(boulderViewer);
        Mockito.when(builder.getDozerViewer()).thenReturn(dozerViewer);
        Mockito.when(builder.getTargetViewer()).thenReturn(targetViewer);
        Mockito.when(builder.getWallViewer()).thenReturn(wallViewer).thenReturn(wallViewer);
        viewer.drawElements(gui);

        Mockito.verify(dozerViewer, Mockito.times(1)).draw(Mockito.any(), Mockito.eq(gui));
        Mockito.verify(targetViewer, Mockito.times(2)).draw(Mockito.any(), Mockito.eq(gui));
        Mockito.verify(boulderViewer, Mockito.times(2)).draw(Mockito.any(), Mockito.eq(gui));
        Mockito.verify(wallViewer, Mockito.times(6)).draw(Mockito.any(), Mockito.eq(gui));
    }

    @Test
    public void dozerViewerTest() {
        DozerViewer viewer = new DozerViewer();
        Dozer mock = Mockito.mock(Dozer.class);
        Mockito.when(mock.getPosition()).thenReturn(null);

        viewer.draw(mock, gui);

        Mockito.verify(gui, Mockito.times(1)).drawDozer(Mockito.any());
    }

    @Test
    public void boulderViewerTest() {
        BoulderViewer viewer = new BoulderViewer();
        Boulder mock = Mockito.mock(Boulder.class);
        Mockito.when(mock.getPosition()).thenReturn(null);

        viewer.draw(mock, gui);

        Mockito.verify(gui, Mockito.times(1)).drawBoulder(Mockito.any());
    }

    @Test
    public void targetViewerTest() {
        TargetViewer viewer = new TargetViewer();
        Target mock = Mockito.mock(Target.class);
        Mockito.when(mock.getPosition()).thenReturn(null);

        viewer.draw(mock, gui);

        Mockito.verify(gui, Mockito.times(1)).drawTarget(Mockito.any());
    }

    @Test
    public void wallViewerTest() {
        WallViewer viewer = new WallViewer();
        Wall mock = Mockito.mock(Wall.class);
        Mockito.when(mock.getPosition()).thenReturn(null);

        viewer.draw(mock, gui);

        Mockito.verify(gui, Mockito.times(1)).drawWall(Mockito.any());
    }
}
