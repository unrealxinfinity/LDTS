package pt.up.fe.edu.hero.viewer.game;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall>{
    @Override
    public void draw(Wall element, GUI gui) {
        gui.drawWall(element.getPosition());
    }
}
