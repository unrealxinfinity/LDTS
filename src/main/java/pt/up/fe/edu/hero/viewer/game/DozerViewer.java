package pt.up.fe.edu.hero.viewer.game;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.elements.Dozer;

public class DozerViewer implements ElementViewer<Dozer> {
    @Override
    public void draw(Dozer element, GUI gui) {
        gui.drawDozer(element.getPosition());
    }
}
