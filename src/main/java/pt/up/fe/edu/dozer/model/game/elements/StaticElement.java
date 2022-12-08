package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public abstract class StaticElement extends Element{
    public StaticElement(int x, int y) {
        super(x,y);
    }

    public StaticElement(Position position) {
        super(position);
    }

    @Override
    public void setPosition(Position position) {}
}
