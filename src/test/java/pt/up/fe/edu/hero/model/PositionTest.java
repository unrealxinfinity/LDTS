package pt.up.fe.edu.hero.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void getXTest() {
        Position position = new Position(1,2);

        Assertions.assertEquals(1, position.getX());
    }

    @Test
    public void getYTest() {
        Position position = new Position(3,4);

        Assertions.assertEquals(4, position.getY());
    }

    @Test
    public void equalsTest() {
        Position position = new Position(5,6);
        Position position_other = new Position(5,6);

        Assertions.assertEquals(position, position_other);
        Assertions.assertEquals(position_other, position);
    }

    @Test
    public void notEqualsTest() {
        Position position = new Position(0,-1);
        Position position_other = new Position(37,42);

        Assertions.assertNotEquals(position, position_other);
        Assertions.assertNotEquals(position_other, position);
    }
    @Test
    public void moveLeftTest(){
        Position position= new Position(1,0);
        Position target = new Position(0,0);
        Assertions.assertEquals(target,position.moveLeft());
    }
    @Test
    public void moveRightTest(){
        Position position= new Position(0,0);
        Position target = new Position(1,0);
        Assertions.assertEquals(target,position.moveRight());
    }
    @Test
    public void moveUpTest(){
        Position position= new Position(0,1);
        Position target = new Position(0,0);
        Assertions.assertEquals(target,position.moveUp());
    }
    @Test
    public void moveDownTest(){
        Position position= new Position(0,0);
        Position target = new Position(0,1);
        Assertions.assertEquals(target,position.moveDown());
    }


}
