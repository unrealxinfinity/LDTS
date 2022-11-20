package pt.up.fe.edu.hero.model.game.elements;

import pt.up.fe.edu.hero.model.Position;

public abstract class Element {
    protected Position position;

    public Element(int x, int y) {
        this.position = new Position(x,y);
    }

    public Position getPosition() {
        return this.position;
    }
}
