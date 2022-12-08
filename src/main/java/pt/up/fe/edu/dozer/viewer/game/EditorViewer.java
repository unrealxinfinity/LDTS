package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.viewer.Viewer;

public class EditorViewer extends Viewer<EditorArena> {
    private final GameViewer viewer;
    private final PlacerViewerBuilder builder;

    public EditorViewer(EditorArena arena, PlacerViewerBuilder builder, GameViewer viewer) {
        super(arena);
        this.builder = builder;
        this.viewer = viewer;
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        viewer.drawElements(gui, time);
        PlacerViewer placerViewer = builder.getPlacerViewer();
        placerViewer.draw(getModel().getPlacer(), gui);
    }

}
