package pt.up.fe.edu.dozer.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.*;
import pt.up.fe.edu.dozer.viewer.editor.PlacerViewer;
import pt.up.fe.edu.dozer.viewer.game.BoulderViewer;
import pt.up.fe.edu.dozer.viewer.game.DozerViewer;
import pt.up.fe.edu.dozer.viewer.game.TargetViewer;
import pt.up.fe.edu.dozer.viewer.game.WallViewer;

public class ElementViewerTest {
    GUI gui;
    Position position;

    @BeforeEach
    public void setUp() {
        gui = Mockito.mock(GUI.class);
        position = Mockito.mock(Position.class);
    }

    @Test
    public void dozerViewerTest() {
        DozerViewer viewer = new DozerViewer();
        Dozer element = Mockito.mock(Dozer.class);
        Mockito.when(element.getPosition()).thenReturn(position);

        viewer.draw(element, gui);

        Mockito.verify(element, Mockito.times(1)).getPosition();
        Mockito.verify(gui, Mockito.times(1)).drawDozer(Mockito.eq(position));
    }

    @Test
    public void targetViewerTest() {
        TargetViewer viewer = new TargetViewer();
        Target element = Mockito.mock(Target.class);
        Mockito.when(element.getPosition()).thenReturn(position);

        viewer.draw(element, gui);

        Mockito.verify(element, Mockito.times(1)).getPosition();
        Mockito.verify(gui, Mockito.times(1)).drawTarget(Mockito.eq(position));
    }

    @Test
    public void boulderViewerTest() {
        BoulderViewer viewer = new BoulderViewer();
        Boulder element = Mockito.mock(Boulder.class);
        Mockito.when(element.getPosition()).thenReturn(position);

        viewer.draw(element, gui);

        Mockito.verify(element, Mockito.times(1)).getPosition();
        Mockito.verify(gui, Mockito.times(1)).drawBoulder(Mockito.eq(position));
    }

    @Test
    public void wallViewerTest() {
        WallViewer viewer = new WallViewer();
        Wall element = Mockito.mock(Wall.class);
        Mockito.when(element.getPosition()).thenReturn(position);

        viewer.draw(element, gui);

        Mockito.verify(element, Mockito.times(1)).getPosition();
        Mockito.verify(gui, Mockito.times(1)).drawWall(Mockito.eq(position));
    }

    @Test
    public void importantWallViewerTest() {
        WallViewer viewer = new WallViewer();
        ImportantWall element = Mockito.mock(ImportantWall.class);
        Mockito.when(element.getPosition()).thenReturn(position);

        viewer.draw(element, gui);

        Mockito.verify(element, Mockito.times(1)).getPosition();
        Mockito.verify(gui, Mockito.times(1)).drawWall(Mockito.eq(position));
    }

    @Test
    public void placerViewerTest() {
        PlacerViewer viewer = new PlacerViewer();
        Placer element = Mockito.mock(Placer.class);
        Mockito.when(element.getPosition()).thenReturn(position);

        viewer.draw(element, gui);

        Mockito.verify(element, Mockito.times(1)).getPosition();
        Mockito.verify(gui, Mockito.times(1)).drawPlacer(Mockito.eq(position));
    }
}
