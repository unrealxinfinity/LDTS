package pt.up.fe.edu.dozer.model.game.arena;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.game.elements.*;

import java.util.Arrays;

public class EditorArenaTest {
    @Test
    public void getPlacer() {
        EditorArena arena = new EditorArena(20, 20);

        Assertions.assertNotNull(arena.getPlacer());
    }

    @Test
    public void getArena() {
        EditorArena arena = new EditorArena(20, 20);
        arena.setPlacer(new Placer(5,5));
        arena.setDozer(new Dozer(5,5));
        arena.setBoulders(Arrays.asList(new Boulder(3,4), new Boulder(7,7)));
        arena.setTargets(Arrays.asList(new Target(7,7), new Target(8,9)));
        arena.setCollisionWalls(Arrays.asList(new ImportantWall(3,3), new ImportantWall(11,11), new ImportantWall(13,14)));

        EditorArena copy = arena.getArena();

        Assertions.assertEquals(arena.getDozer().getPosition(), copy.getDozer().getPosition());
        Assertions.assertNotSame(arena.getDozer(), copy.getDozer());
        for (int i = 0; i < Integer.max(arena.getBoulders().size(), copy.getBoulders().size()); i++) {
            Assertions.assertEquals(arena.getBoulders().get(i).getPosition(), copy.getBoulders().get(i).getPosition());
            Assertions.assertNotSame(arena.getBoulders().get(i), copy.getBoulders().get(i));
        }
        for (int i = 0; i < Integer.max(arena.getTargets().size(), copy.getTargets().size()); i++) {
            Assertions.assertEquals(arena.getTargets().get(i).getPosition(), copy.getTargets().get(i).getPosition());
            Assertions.assertNotSame(arena.getTargets().get(i), copy.getTargets().get(i));
        }
        for (int i = 0; i < Integer.max(arena.getCollisionWalls().size(), copy.getCollisionWalls().size()); i++) {
            Assertions.assertEquals(arena.getCollisionWalls().get(i).getPosition(), copy.getCollisionWalls().get(i).getPosition());
            Assertions.assertNotSame(arena.getCollisionWalls().get(i), copy.getCollisionWalls().get(i));
        }
    }
}
