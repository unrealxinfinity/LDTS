package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;

public class EditorArena extends Arena {
    public EditorArena(int width, int height) {
        super(width, height);

        this.placer = new Placer(5, 5);
    }

    public EditorArena getArena() {
        EditorArena arena = new EditorArena(getWidth(), getHeight());
        int x = getDozer().getPosition().getX();
        int y = getDozer().getPosition().getY();
        arena.setDozer(new Dozer(x, y));

        createWalls(arena);
        createTargets(arena);
        createBoulders(arena);
        return arena;
    }

    private void createBoulders(EditorArena arena) {
        int x;
        int y;
        List<Boulder> boulders = new ArrayList<>();
        for (Boulder boulder : getBoulders()) {
            x = boulder.getPosition().getX();
            y = boulder.getPosition().getY();
            boulders.add(new Boulder(x, y));
        }
        arena.setBoulders(boulders);
    }

    private void createTargets(EditorArena arena) {
        int x;
        int y;
        List<Target> targets = new ArrayList<>();
        for (Target target : getTargets()) {
            x = target.getPosition().getX();
            y = target.getPosition().getY();
            targets.add(new Target(x, y));
        }
        arena.setTargets(targets);
    }

    private void createWalls(EditorArena arena) {
        int x;
        int y;
        List<Wall> walls = new ArrayList<>();
        for (Wall wall : getCollisionWalls()) {
            x = wall.getPosition().getX();
            y = wall.getPosition().getY();
            walls.add(new ImportantWall(x, y));
        }
        arena.setCollisionWalls(walls);
    }

    public Placer getPlacer() {
        return placer;
    }

    public void setPlacer(Placer placer) {
        this .placer = placer;
    }

    private Placer placer;
}
