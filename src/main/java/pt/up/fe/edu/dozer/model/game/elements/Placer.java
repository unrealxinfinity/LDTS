package pt.up.fe.edu.dozer.model.game.elements;

public class Placer extends Element{
    public Placer(int x, int y, Element element) {
        super(x,y);
        this.element=element;
    }

    public void updateElement(Element element) {
        this.element = element;
    }

    private Element element;

}
