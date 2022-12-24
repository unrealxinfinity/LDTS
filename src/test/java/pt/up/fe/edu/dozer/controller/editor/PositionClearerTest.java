package pt.up.fe.edu.dozer.controller.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionClearerTest {
    @Test
    public void clearPositionDozer() {
        EditorArena arena = Mockito.mock(EditorArena.class);
        Mockito.when(arena.getDozer()).thenReturn(new Dozer(2, 3));
        EditorPositionClearer clearer = new EditorPositionClearer(arena);
        clearer.clearPosition(new Position(2, 3));
        Mockito.verify(arena, Mockito.times(2)).getDozer();
        Mockito.verify(arena, Mockito.times(1)).getCollisionWalls();
        Mockito.verify(arena, Mockito.times(1)).getBoulders();
        Mockito.verify(arena, Mockito.times(1)).getTargets();
        Mockito.verify(arena, Mockito.times(1)).setDozer(Mockito.eq(null));
    }

    @Test
    public void clearPositionNull() {
        EditorArena arena = Mockito.mock(EditorArena.class);
        Mockito.when(arena.getDozer()).thenReturn(null);
        EditorPositionClearer clearer = new EditorPositionClearer(arena);
        clearer.clearPosition(new Position(5, 5));
        Mockito.verify(arena, Mockito.times(1)).getDozer();
        Mockito.verify(arena, Mockito.times(1)).getCollisionWalls();
        Mockito.verify(arena, Mockito.times(1)).getBoulders();
        Mockito.verify(arena, Mockito.times(1)).getTargets();
    }

    public EditorArena setUpArena(int placerX, int placerY) {
        EditorArena arena = new EditorArena(20, 20);
        arena.setPlacer(new Placer(placerX, placerY));
        arena.setDozer(new Dozer(2, 3));
        arena.setBoulders(new ArrayList<>(Arrays.asList(new Boulder(1, 1), new Boulder(6, 7), new Boulder(6, 12))));
        arena.setTargets(new ArrayList<>(Arrays.asList(new Target(2, 2), new Target(10, 10), new Target(18, 2))));
        arena.setCollisionWalls(new ArrayList<>(Arrays.asList(new ImportantWall(2, 1), new ImportantWall(10, 20), new ImportantWall(15, 5))));
        return arena;
    }

    public void assertPositions(EditorArena arena, Position expectedPlacer, Position expectedDozer, List<Position> expectedBoulders, List<Position> expectedTargets, List<Position> expectedWalls) {
        Assertions.assertEquals(expectedPlacer, arena.getPlacer().getPosition());
        Assertions.assertEquals(expectedDozer, arena.getDozer().getPosition());
        for (int i = 0; i < Integer.max(expectedBoulders.size(), arena.getBoulders().size()); i++) {
            Assertions.assertEquals(expectedBoulders.get(i), arena.getBoulders().get(i).getPosition());
        }
        for (int i = 0; i < Integer.max(expectedTargets.size(), arena.getTargets().size()); i++) {
            Assertions.assertEquals(expectedTargets.get(i), arena.getTargets().get(i).getPosition());
        }
        for (int i = 0; i < Integer.max(expectedWalls.size(), arena.getCollisionWalls().size()); i++) {
            Assertions.assertEquals(expectedWalls.get(i), arena.getCollisionWalls().get(i).getPosition());
        }
    }

    @Test
    public void testWithArenaWall() {
        EditorArena arena = setUpArena(15, 5);
        Position expectedPlacer = new Position(15, 5);
        Position expectedDozer = new Position(2, 3);
        List<Position> expectedBoulders = Arrays.asList(new Position(1, 1), new Position(6, 7), new Position(6, 12));
        List<Position> expectedTargets = Arrays.asList(new Position(2, 2), new Position(10, 10), new Position(18, 2));
        List<Position> expectedWalls = Arrays.asList(new Position(2, 1), new Position(10, 20));

        EditorPositionClearer clearer = new EditorPositionClearer(arena);
        clearer.clearPosition(new Position(15, 5));

        assertPositions(arena, expectedPlacer, expectedDozer, expectedBoulders, expectedTargets, expectedWalls);
    }

    @Test
    public void testWithArenaBoulder() {
        EditorArena arena = setUpArena(1, 1);
        Position expectedPlacer = new Position(1, 1);
        Position expectedDozer = new Position(2, 3);
        List<Position> expectedBoulders = Arrays.asList(new Position(6, 7), new Position(6, 12));
        List<Position> expectedTargets = Arrays.asList(new Position(2, 2), new Position(10, 10), new Position(18, 2));
        List<Position> expectedWalls = Arrays.asList(new Position(2, 1), new Position(10, 20), new Position(15, 5));

        EditorPositionClearer clearer = new EditorPositionClearer(arena);
        clearer.clearPosition(new Position(1, 1));

        assertPositions(arena, expectedPlacer, expectedDozer, expectedBoulders, expectedTargets, expectedWalls);
    }

    @Test
    public void testWithArenaTarget() {
        EditorArena arena = setUpArena(10, 10);
        Position expectedPlacer = new Position(10, 10);
        Position expectedDozer = new Position(2, 3);
        List<Position> expectedBoulders = Arrays.asList(new Position(1, 1), new Position(6, 7), new Position(6, 12));
        List<Position> expectedTargets = Arrays.asList(new Position(2, 2), new Position(18, 2));
        List<Position> expectedWalls = Arrays.asList(new Position(2, 1), new Position(10, 20), new Position(15, 5));

        EditorPositionClearer clearer = new EditorPositionClearer(arena);
        clearer.clearPosition(new Position(10, 10));

        assertPositions(arena, expectedPlacer, expectedDozer, expectedBoulders, expectedTargets, expectedWalls);
    }
}
