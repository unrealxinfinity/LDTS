package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Element;
import pt.up.fe.edu.dozer.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {
    private final ElementViewerBuilder builder;
    public GameViewer(Arena arena, ElementViewerBuilder builder) {
        super(arena);
        this.builder = builder;
    }

    @Override
    public void drawElements(GUI gui,long time) {
        drawElements(gui, getModel().getWalls(), builder.getWallViewer());
        drawElements(gui, getModel().getCollisionWalls(), builder.getWallViewer());
        drawElements(gui, getModel().getTargets(), builder.getTargetViewer());
        drawElements(gui, getModel().getBoulders(), builder.getBoulderViewer());
        if (getModel().getDozer() != null) drawElement(gui, getModel().getDozer(), builder.getDozerViewer());
        gui.drawTime(new Position(15,0),time,"#FFFFFF");
        if (getModel().getLevelNum() != -1) gui.drawText(new Position(0,0), "Level " + getModel().getLevelNum(), "#FFFFFF");
        gui.drawText(new Position(1,13),"Press R to restart","#0000FF");
        gui.drawText(new Position(1,14),"Press ESC for menu","#0000FF");
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
