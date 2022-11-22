package pt.up.fe.edu.hero.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.arena.Arena;
import pt.up.fe.edu.hero.model.game.elements.*;
import pt.up.fe.edu.hero.viewer.game.GameViewer;

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

        GameViewer viewer = new GameViewer(arena);
        viewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(6)).drawWall(Mockito.any());
        Mockito.verify(gui, Mockito.times(2)).drawTarget(Mockito.any());
        Mockito.verify(gui, Mockito.times(2)).drawBoulder(Mockito.any());
        Mockito.verify(gui, Mockito.times(1)).drawDozer(Mockito.any());
    }
}
