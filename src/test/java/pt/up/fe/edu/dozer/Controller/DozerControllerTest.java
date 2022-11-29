package pt.up.fe.edu.dozer.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.controller.gameController.DozerController;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.model.game.elements.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DozerControllerTest {
    @Test
    public void moveDozer(){
        Arena arena = new Arena(10,10);
        List<Wall> collisionWalls=new ArrayList<>();
        collisionWalls.add(new Wall(2,2));
        arena.setCollisionWalls(collisionWalls);
        arena.setDozer(new Dozer(0,0));
        DozerController dozerC=new DozerController(arena);

        dozerC.moveDozerRight();
        Assertions.assertEquals(new Position(1,0),arena.getDozer().getPosition() );
        dozerC.moveDozerDown();
        Assertions.assertEquals(new Position(1,1),arena.getDozer().getPosition());
        dozerC.moveDozerUP();
        Assertions.assertEquals(new Position(1,0),arena.getDozer().getPosition());
        dozerC.moveDozerLeft();
        Assertions.assertEquals(new Position(0,0),arena.getDozer().getPosition());
    }
}
