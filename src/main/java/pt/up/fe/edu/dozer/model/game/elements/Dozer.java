package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public class Dozer extends Element {
    public Dozer(int x, int y) {
        super(x, y);
    }

    public Dozer(Position position) {
        super(position);
    }
}
