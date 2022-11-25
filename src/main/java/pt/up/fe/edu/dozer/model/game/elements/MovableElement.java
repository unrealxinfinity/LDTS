package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public abstract class MovableElement extends Element{
    public MovableElement(int x, int y) {
        super(x, y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
