package pt.up.fe.edu.dozer.model.game.arena;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditorArenaTest {
    @Test
    public void getPlacer() {
        EditorArena arena = new EditorArena(20, 20);

        Assertions.assertNotNull(arena.getPlacer());
    }
}
