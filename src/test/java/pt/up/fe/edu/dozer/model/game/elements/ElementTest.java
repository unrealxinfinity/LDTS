package pt.up.fe.edu.dozer.model.game.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.Position;

public class ElementTest {
    @Test
    public void moveDozerTest() {
        Dozer dozer = new Dozer(1, 2);

        dozer.setPosition(new Position(6, 6));

        Assertions.assertEquals(new Position(6, 6), dozer.getPosition());
    }

    @Test
    public void moveBoulderTest() {
        Boulder boulder = new Boulder(new Position(1, 2));

        boulder.setPosition(new Position(6, 6));

        Assertions.assertEquals(new Position(6, 6), boulder.getPosition());
    }

    @Test
    public void moveStaticElement() {
        Position position = new Position(3, 4);
        Wall wall = new Wall(position);

        wall.setPosition(new Position(4, 4));

        Assertions.assertEquals(position, wall.getPosition());
    }
}
