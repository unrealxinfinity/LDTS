package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;

public class CopyArenaBuilder extends ArenaBuilder{

    private final Arena baseArena;

    public CopyArenaBuilder(Arena baseArena) {
        this.baseArena = baseArena;
    }
    @Override
    protected int getWidth() {
        return baseArena.getWidth();
    }

    @Override
    protected int getHeight() {
        return baseArena.getHeight();
    }

    @Override
    protected Dozer createDozer() {
        int x = baseArena.getDozer().getPosition().getX();
        int y = baseArena.getDozer().getPosition().getY();
        return new Dozer(x, y);
    }

    @Override
    protected List<Wall> createWalls() {
        int x;
        int y;
        List<Wall> walls = new ArrayList<>();
        for (Wall wall : baseArena.getCollisionWalls()) {
            x = wall.getPosition().getX();
            y = wall.getPosition().getY();
            walls.add(new Wall(x, y));
        }
        return walls;
    }

    @Override
    protected List<Wall> createCollisionWalls() {
        int x;
        int y;
        List<Wall> walls = new ArrayList<>();
        for (Wall wall : baseArena.getCollisionWalls()) {
            x = wall.getPosition().getX();
            y = wall.getPosition().getY();
            walls.add(new ImportantWall(x, y));
        }
        return walls;
    }

    @Override
    protected List<Boulder> createBoulders() {
        int x;
        int y;
        List<Boulder> boulders = new ArrayList<>();
        for (Boulder boulder : baseArena.getBoulders()) {
            x = boulder.getPosition().getX();
            y = boulder.getPosition().getY();
            boulders.add(new Boulder(x, y));
        }
        return boulders;
    }

    @Override
    protected List<Target> createTargets() {
        int x;
        int y;
        List<Target> targets = new ArrayList<>();
        for (Target target : baseArena.getTargets()) {
            x = target.getPosition().getX();
            y = target.getPosition().getY();
            targets.add(new Target(x, y));
        }
        return targets;
    }
}
