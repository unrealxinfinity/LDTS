package pt.up.fe.edu.hero.viewer.game;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.elements.Target;

public class TargetViewer implements ElementViewer<Target>{
    @Override
    public void draw(Target element, GUI gui) {
        gui.drawTarget(element.getPosition());
    }
}
