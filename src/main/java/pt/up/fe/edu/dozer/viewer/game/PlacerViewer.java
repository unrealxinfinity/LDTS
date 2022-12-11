package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.elements.Placer;

public class PlacerViewer implements ElementViewer<Placer> {

    @Override
    public void draw(Placer element, GUI gui) {
        gui.drawPlacer(element.getPosition());
    }
}
