package pt.up.fe.edu.dozer.model.game.arena;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.*;

import java.util.Arrays;
import java.util.List;

public class ArenaTest {
    private Arena arena;

    @BeforeEach
    public void setUp() {
        arena = new Arena(20,20);
    }

    @Test
    public void isBoulderTest() {
        List<Boulder> boulders = Arrays.asList(new Boulder(3,4), new Boulder(6,5));
        arena.setBoulders(boulders);

        boolean bool = arena.isBoulder(new Position(3,4));

        Assertions.assertTrue(bool);
    }

    @Test
    public void isNotBoulderTest() {
        List<Boulder> boulders = Arrays.asList(new Boulder(3,4), new Boulder(6,5));
        arena.setBoulders(boulders);

        boolean bool = arena.isBoulder(new Position(6,4));

        Assertions.assertFalse(bool);
    }

    @Test
    public void isTargetTest() {
        List<Target> targets = Arrays.asList(new Target(3,4), new Target(6,5));
        arena.setTargets(targets);

        boolean bool = arena.isTarget(new Position(6,5));

        Assertions.assertTrue(bool);
    }

    @Test
    public void isNotTargetTest() {
        List<Target> targets = Arrays.asList(new Target(3,4), new Target(6,5));
        arena.setTargets(targets);

        boolean bool = arena.isTarget(new Position(1,5));

        Assertions.assertFalse(bool);
    }

    @Test
    public void isNotWallTest() {
        List<Wall> collisionWalls = Arrays.asList(new ImportantWall(3,4), new ImportantWall(6,5));
        arena.setCollisionWalls(collisionWalls);

        boolean bool = arena.isWall(new Position(2,5));

        Assertions.assertFalse(bool);
    }

    @Test
    public void isWallTest() {
        List<Wall> collisionWalls = Arrays.asList(new ImportantWall(3,4), new ImportantWall(6,5));
        arena.setCollisionWalls(collisionWalls);

        boolean bool = arena.isWall(new Position(3,4));

        Assertions.assertTrue(bool);
    }

    @Test
    public void dozerNotNullTest() {
        arena.setDozer(new Dozer(1,2));

        Dozer dozer = arena.getDozer();

        Assertions.assertNotNull(dozer);
    }
}
