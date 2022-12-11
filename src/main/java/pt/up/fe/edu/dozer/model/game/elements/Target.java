package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public class Target extends StaticElement {
    public Target(int x, int y) {
        super(x, y);
    }

    public Target(Position position) {
        super(position);
    }
}
