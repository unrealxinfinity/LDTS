package pt.up.fe.edu.hero.viewer.game;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.elements.Element;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui);
}
