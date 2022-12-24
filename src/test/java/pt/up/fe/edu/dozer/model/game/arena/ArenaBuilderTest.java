package pt.up.fe.edu.dozer.model.game.arena;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.model.game.elements.Target;
import pt.up.fe.edu.dozer.model.game.elements.Wall;

import java.io.IOException;
import java.util.List;

public class ArenaBuilderTest {
    private LevelReader reader;


    @BeforeEach
    public void setUp() throws IOException {
        reader = Mockito.mock(LevelReader.class);
        List<String> lines = List.of("#########W+W########", "#########W WWW######", "########WW& &+W#####", "#######W+ &*WW######");

        Mockito.when(reader.readLevel(Mockito.anyInt())).thenReturn(lines);
    }

    @Test
    public void getHeightTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);

        Assertions.assertEquals(4, builder.getHeight());
    }

    @Test
    public void getWidthTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);

        Assertions.assertEquals(20, builder.getWidth());
    }

    @Test
    public void createDozerTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);
        Position expected = new Position(11, 3);

        Dozer dozer = builder.createDozer();
        Position dozerPosition = dozer.getPosition();

        Assertions.assertEquals(expected, dozerPosition);
    }

    @Test
    public void createBouldersTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);

        List<Boulder> boulders = builder.createBoulders();

        Assertions.assertEquals(3, boulders.size());
    }

    @Test
    public void createTargetsTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);

        List<Target> targets = builder.createTargets();

        Assertions.assertEquals(3, targets.size());
    }

    @Test
    public void createWallsTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);

        List<Wall> walls = builder.createWalls();

        Assertions.assertEquals(58, walls.size());
    }

    @Test
    public void createCollisionWallsTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);

        List<Wall> collisionWalls = builder.createCollisionWalls();

        Assertions.assertEquals(12, collisionWalls.size());
    }

    @Test
    public void getLevelNumTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);

        Assertions.assertEquals(42, builder.getLevelNum());
    }

    @Test
    public void createArenaTest() throws IOException {
        LoaderArenaBuilder builder = new LoaderArenaBuilder(42, reader);
        Arena arena = Mockito.mock(Arena.class);
        InOrder order = Mockito.inOrder(arena);

        builder.createArena(arena);

        order.verify(arena, Mockito.times(1)).setHeight(Mockito.anyInt());
        order.verify(arena, Mockito.times(1)).setWidth(Mockito.anyInt());
        order.verify(arena, Mockito.times(1)).setLevelNum(Mockito.anyInt());
        order.verify(arena, Mockito.times(1)).setDozer(Mockito.any());
        order.verify(arena, Mockito.times(1)).setWalls(Mockito.any());
        order.verify(arena, Mockito.times(1)).setCollisionWalls(Mockito.any());
        order.verify(arena, Mockito.times(1)).setBoulders(Mockito.any());
        order.verify(arena, Mockito.times(1)).setTargets(Mockito.any());
    }
}
