package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.viewer.Viewer;

public class EditorViewer extends Viewer<EditorArena> {
    private final GameViewer viewer;
    //NEW
    private String placingElem;
// Added a parameter for the EditorViewer to take
    public EditorViewer(EditorArena arena,String elem) {
        super(arena);
        this.viewer = new GameViewer(arena, new ElementViewerBuilder());
        this.placingElem=elem;
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        viewer.drawElements(gui, time);
        PlacerViewer placerViewer = new PlacerViewer();
        gui.drawText(new Position(0,0),"Placing: "+placingElem,"#FFFFFF");
        placerViewer.draw(getModel().getPlacer(), gui);
    }

}
