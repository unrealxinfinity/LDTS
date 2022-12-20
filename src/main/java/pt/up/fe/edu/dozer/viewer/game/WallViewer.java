package pt.up.fe.edu.dozer.viewer.game;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall> {
    @Override
    public void draw(Wall element, GUI gui) {
        Position position = element.getPosition();
        gui.drawWall(position);
    }
}
