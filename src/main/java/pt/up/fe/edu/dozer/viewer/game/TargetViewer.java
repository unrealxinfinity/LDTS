package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.Target;

public class TargetViewer implements ElementViewer<Target> {
    @Override
    public void draw(Target element, GUI gui) {
        Position position = element.getPosition();
        gui.drawTarget(position);
    }
}
