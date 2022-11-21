package pt.up.fe.edu.hero.model.game.arena;

import pt.up.fe.edu.hero.model.Position;
import pt.up.fe.edu.hero.model.game.elements.*;

import java.util.List;

public class Arena {
    private final int height;
    private final int width;

    private Dozer dozer;

    private List<Target> targets;
    private List<Wall> walls;
    private List<ImportantWall> collisionWalls;
    private List<Boulder> boulders;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setDozer(Dozer dozer) {
        this.dozer = dozer;
    }

    public void setBoulders(List<Boulder> boulders) {
        this.boulders = boulders;
    }

    public void setCollisionWalls(List<ImportantWall> collisionWalls) {
        this.collisionWalls = collisionWalls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public boolean isBoulder(Position position) {
        for (Boulder boulder : boulders)
            if (boulder.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean isTarget(Position position) {
        for (Target target : targets)
            if (target.getPosition().equals(position))
                return true;
        return false;

    }

    public boolean isNotWall(Position position) {
        for (ImportantWall importantWall : collisionWalls)
            if (importantWall.getPosition().equals(position))
                return true;
        return false;
    }
}
