package pt.up.fe.edu.hero.model.game.elements;

import pt.up.fe.edu.hero.model.Position;

public abstract class MovableElement extends Element{
    public MovableElement(int x, int y) {
        super(x, y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
