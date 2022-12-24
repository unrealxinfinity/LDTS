package pt.up.fe.edu.dozer.controller.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.model.game.elements.Placer;
import pt.up.fe.edu.dozer.model.game.elements.Target;
import pt.up.fe.edu.dozer.model.game.elements.Wall;

import java.util.ArrayList;
import java.util.List;

public class ElementPlacingTest {
    private EditorArena arena;
    private List<Boulder> boulderList = new ArrayList<>();
    private List<Target> targetList = new ArrayList<>();
    private List<Wall> wallList = new ArrayList<>();
    private Placer placer;
    private final Position placerPosition = new Position(5, 5);

    @BeforeEach
    public void setUp() {
        arena = Mockito.mock(EditorArena.class);
        placer = Mockito.mock(Placer.class);
        Mockito.when(placer.getPosition()).thenReturn(placerPosition);
        Mockito.when(arena.getBoulders()).thenReturn(boulderList);
        Mockito.when(arena.getCollisionWalls()).thenReturn(wallList);
        Mockito.when(arena.getTargets()).thenReturn(targetList);
        Mockito.when(arena.getPlacer()).thenReturn(placer);
    }

    @Test
    public void boulderTest() {
        EditorArenaBoulderController controller = new EditorArenaBoulderController(arena);

        controller.placeElement();

        Mockito.verify(placer, Mockito.times(1)).getPosition();
        Mockito.verify(arena, Mockito.times(1)).getBoulders();
        Assertions.assertEquals(1, boulderList.size());
        Assertions.assertEquals(placerPosition, boulderList.get(0).getPosition());
    }

    @Test
    public void targetTest() {
        EditorArenaTargetController controller = new EditorArenaTargetController(arena);

        controller.placeElement();

        Mockito.verify(placer, Mockito.times(1)).getPosition();
        Mockito.verify(arena, Mockito.times(1)).getTargets();
        Assertions.assertEquals(1, targetList.size());
        Assertions.assertEquals(placerPosition, targetList.get(0).getPosition());
    }

    @Test
    public void wallTest() {
        EditorArenaWallController controller = new EditorArenaWallController(arena);

        controller.placeElement();

        Mockito.verify(placer, Mockito.times(1)).getPosition();
        Mockito.verify(arena, Mockito.times(1)).getCollisionWalls();
        Assertions.assertEquals(1, wallList.size());
        Assertions.assertEquals(placerPosition, wallList.get(0).getPosition());
    }
}
