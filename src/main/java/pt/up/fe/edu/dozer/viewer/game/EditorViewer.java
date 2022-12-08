package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.viewer.Viewer;

public class EditorViewer extends Viewer<EditorArena> {
    private final GameViewer viewer;

    public EditorViewer(EditorArena arena) {
        super(arena);
        this.viewer = new GameViewer(arena, new ElementViewerBuilder());
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        viewer.drawElements(gui, time);
        PlacerViewer placerViewer = new PlacerViewer();
        placerViewer.draw(getModel().getPlacer(), gui);
    }

}
