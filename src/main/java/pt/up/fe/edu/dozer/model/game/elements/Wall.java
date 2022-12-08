package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public class Wall extends StaticElement{
    public Wall(int x, int y) {
        super(x, y);
    }

    public Wall(Position position) {
        super(position);
    }
}
