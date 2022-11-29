package pt.up.fe.edu.dozer.model.game.elements;

import pt.up.fe.edu.dozer.model.Position;

public abstract class StaticElement extends Element{
    public StaticElement(int x, int y) {
        super(x,y);
    }

    @Override
    public void setPosition(Position position) {}
}
