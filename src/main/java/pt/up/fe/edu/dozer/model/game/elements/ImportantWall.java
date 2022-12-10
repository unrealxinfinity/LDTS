package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public class ImportantWall extends Wall {
    public ImportantWall(int x, int y) {
        super(x, y);
    }

    public ImportantWall(Position position) {
        super(position);
    }
}
