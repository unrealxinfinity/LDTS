package pt.up.fe.edu.dozer.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.*;
import pt.up.fe.edu.dozer.viewer.game.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameViewerTest {
    private GUI gui;

    @BeforeEach
    public void setUp() {
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawElementsTest() {
        Arena arena = new Arena(20, 20);
        arena.setDozer(new Dozer(5, 5));
        arena.setBoulders(Arrays.asList(new Boulder(3, 4), new Boulder(7, 7)));
        arena.setTargets(Arrays.asList(new Target(7, 7), new Target(8, 9)));
        arena.setWalls(Arrays.asList(new Wall(1, 1), new Wall(2, 2), new Wall(10, 10)));
        arena.setCollisionWalls(Arrays.asList(new ImportantWall(3, 3), new ImportantWall(11, 11), new ImportantWall(13, 14)));

        ElementViewerBuilder builder = Mockito.mock(ElementViewerBuilder.class);
        WallViewer wallViewer = Mockito.mock(WallViewer.class);
        DozerViewer dozerViewer = Mockito.mock(DozerViewer.class);
        TargetViewer targetViewer = Mockito.mock(TargetViewer.class);
        BoulderViewer boulderViewer = Mockito.mock(BoulderViewer.class);
        InOrder order = Mockito.inOrder(dozerViewer, targetViewer, boulderViewer, wallViewer, gui);

        GameViewer viewer = new GameViewer(arena, builder);
        Mockito.when(builder.getBoulderViewer()).thenReturn(boulderViewer);
        Mockito.when(builder.getDozerViewer()).thenReturn(dozerViewer);
        Mockito.when(builder.getTargetViewer()).thenReturn(targetViewer);
        Mockito.when(builder.getWallViewer()).thenReturn(wallViewer).thenReturn(wallViewer);
        viewer.drawElements(gui, 0);

        order.verify(wallViewer, Mockito.times(6)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(targetViewer, Mockito.times(2)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(boulderViewer, Mockito.times(2)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(dozerViewer, Mockito.times(1)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(gui, Mockito.times(1)).drawTime(Mockito.any(), Mockito.eq((long) 0), Mockito.any());
        order.verify(gui, Mockito.times(2)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void drawElementsTestWithLevel() {
        Arena arena = new Arena(20, 20, 2);
        arena.setDozer(new Dozer(5, 5));
        arena.setBoulders(Arrays.asList(new Boulder(3, 4), new Boulder(7, 7)));
        arena.setTargets(Arrays.asList(new Target(7, 7), new Target(8, 9)));
        arena.setWalls(Arrays.asList(new Wall(1, 1), new Wall(2, 2), new Wall(10, 10)));
        arena.setCollisionWalls(Arrays.asList(new ImportantWall(3, 3), new ImportantWall(11, 11), new ImportantWall(13, 14)));

        ElementViewerBuilder builder = Mockito.mock(ElementViewerBuilder.class);
        WallViewer wallViewer = Mockito.mock(WallViewer.class);
        DozerViewer dozerViewer = Mockito.mock(DozerViewer.class);
        TargetViewer targetViewer = Mockito.mock(TargetViewer.class);
        BoulderViewer boulderViewer = Mockito.mock(BoulderViewer.class);
        InOrder order = Mockito.inOrder(dozerViewer, targetViewer, boulderViewer, wallViewer, gui);

        GameViewer viewer = new GameViewer(arena, builder);
        Mockito.when(builder.getBoulderViewer()).thenReturn(boulderViewer);
        Mockito.when(builder.getDozerViewer()).thenReturn(dozerViewer);
        Mockito.when(builder.getTargetViewer()).thenReturn(targetViewer);
        Mockito.when(builder.getWallViewer()).thenReturn(wallViewer).thenReturn(wallViewer);
        viewer.drawElements(gui, 0);

        order.verify(wallViewer, Mockito.times(6)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(targetViewer, Mockito.times(2)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(boulderViewer, Mockito.times(2)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(dozerViewer, Mockito.times(1)).draw(Mockito.any(), Mockito.eq(gui));
        order.verify(gui, Mockito.times(1)).drawTime(Mockito.any(), Mockito.eq((long) 0), Mockito.any());
        order.verify(gui, Mockito.times(3)).drawText(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void drawTest() throws IOException {
        InOrder order = Mockito.inOrder(gui);
        Arena arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getBoulders()).thenReturn(new ArrayList<>());
        Mockito.when(arena.getDozer()).thenReturn(new Dozer(1, 1));
        Mockito.when(arena.getCollisionWalls()).thenReturn(new ArrayList<>());
        Mockito.when(arena.getWalls()).thenReturn(new ArrayList<>());
        Mockito.when(arena.getTargets()).thenReturn(new ArrayList<>());
        GameViewer viewer = new GameViewer(arena, new ElementViewerBuilder());

        viewer.draw(gui, 0);

        order.verify(gui, Mockito.times(1)).clear();
        order.verify(gui, Mockito.times(1)).refresh();
    }
}
