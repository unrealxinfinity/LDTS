package pt.up.fe.edu.hero.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.hero.model.game.elements.Boulder;
import pt.up.fe.edu.hero.model.game.elements.Dozer;

public class ElementTest {
    @Test
    public void moveDozerTest() {
        Dozer dozer = new Dozer(1,2);

        dozer.setPosition(new Position(6,6));

        Assertions.assertEquals(new Position(6,6), dozer.getPosition());
    }
    
    @Test
    public void moveBoulderTest() {
        Boulder boulder = new Boulder(1,2);

        boulder.setPosition(new Position(6,6));

        Assertions.assertEquals(new Position(6,6), boulder.getPosition());
    }
}
