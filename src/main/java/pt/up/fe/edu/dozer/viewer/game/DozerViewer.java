package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;

public class DozerViewer implements ElementViewer<Dozer> {
    @Override
    public void draw(Dozer element, GUI gui) {
        Position position = element.getPosition();
        gui.drawDozer(position);
    }
}
