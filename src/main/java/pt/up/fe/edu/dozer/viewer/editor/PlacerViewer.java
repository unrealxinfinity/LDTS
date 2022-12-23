package pt.up.fe.edu.dozer.viewer.editor;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.Placer;
import pt.up.fe.edu.dozer.viewer.game.ElementViewer;

public class PlacerViewer implements ElementViewer<Placer> {

    @Override
    public void draw(Placer element, GUI gui) {
        Position position = element.getPosition();
        gui.drawPlacer(position);
    }
}
