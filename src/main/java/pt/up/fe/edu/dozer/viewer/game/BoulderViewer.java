package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;

public class BoulderViewer implements ElementViewer<Boulder> {
    @Override
    public void draw(Boulder element, GUI gui) {
        gui.drawBoulder(element.getPosition());
    }
}
