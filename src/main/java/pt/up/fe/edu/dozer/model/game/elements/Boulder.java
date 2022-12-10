package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public class Boulder extends Element {
    public Boulder(int x, int y) {
        super(x, y);
    }

    public Boulder(Position position) {
        super(position);
    }
}
