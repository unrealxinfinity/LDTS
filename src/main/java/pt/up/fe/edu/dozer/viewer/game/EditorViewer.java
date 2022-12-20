package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Placer;
import pt.up.fe.edu.dozer.viewer.Viewer;

public class EditorViewer extends Viewer<EditorArena> {
    private final GameViewer viewer;
    private final String placingElem;
    private final PlacerViewerBuilder builder;

    public EditorViewer(EditorArena arena, PlacerViewerBuilder builder, GameViewer viewer, String elem) {
        super(arena);
        this.builder = builder;
        this.viewer = viewer;
        this.placingElem = elem;
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        viewer.drawElements(gui, time);
        PlacerViewer placerViewer = builder.getPlacerViewer();
        gui.drawText(new Position(0, 0), "Placing: " + placingElem, "#FFFFFF");
        Placer placer = getModel().getPlacer();
        placerViewer.draw(placer, gui);
    }

}
