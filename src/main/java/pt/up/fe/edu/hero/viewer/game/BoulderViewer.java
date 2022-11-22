package pt.up.fe.edu.hero.viewer.game;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.elements.Boulder;

public class BoulderViewer implements ElementViewer<Boulder>{
    @Override
    public void draw(Boulder element, GUI gui) {
        gui.drawBoulder(element.getPosition());
    }
}
