package pt.up.fe.edu.hero.viewer.game;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.arena.Arena;
import pt.up.fe.edu.hero.model.game.elements.Element;
import pt.up.fe.edu.hero.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {
    public GameViewer(Arena arena) {
        super(arena);
    }

    @Override
    public void drawElements(GUI gui) {
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElements(gui, getModel().getCollisionWalls(), new WallViewer());
        drawElements(gui, getModel().getTargets(), new TargetViewer());
        drawElements(gui, getModel().getBoulders(), new BoulderViewer());
        drawElement(gui, getModel().getDozer(), new DozerViewer());
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements) {
            drawElement(gui, element, viewer);
        }
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
