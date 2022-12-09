package pt.up.fe.edu.dozer.controller.editor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;

public class PositionClearerTest {
    @Test
    public void clearPositionDozer() {
        EditorArena arena = Mockito.mock(EditorArena.class);
        Mockito.when(arena.getDozer()).thenReturn(new Dozer(2,3));
        EditorPositionClearer clearer = new EditorPositionClearer(arena);
        clearer.clearPosition(new Position(5,5));
        Mockito.verify(arena, Mockito.times(2)).getDozer();
        Mockito.verify(arena, Mockito.times(1)).getCollisionWalls();
        Mockito.verify(arena, Mockito.times(1)).getBoulders();
        Mockito.verify(arena, Mockito.times(1)).getTargets();
    }

    @Test
    public void clearPositionNull() {
        EditorArena arena = Mockito.mock(EditorArena.class);
        Mockito.when(arena.getDozer()).thenReturn(null);
        EditorPositionClearer clearer = new EditorPositionClearer(arena);
        clearer.clearPosition(new Position(5,5));
        Mockito.verify(arena, Mockito.times(1)).getDozer();
        Mockito.verify(arena, Mockito.times(1)).getCollisionWalls();
        Mockito.verify(arena, Mockito.times(1)).getBoulders();
        Mockito.verify(arena, Mockito.times(1)).getTargets();
    }
}
