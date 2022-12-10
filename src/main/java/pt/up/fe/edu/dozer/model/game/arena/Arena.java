package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.model.game.elements.Target;
import pt.up.fe.edu.dozer.model.game.elements.Wall;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int height;
    private int width;

    private Dozer dozer;

    private List<Target> targets;
    private List<Wall> walls;
    private List<Wall> collisionWalls;
    private List<Boulder> boulders;
    private int levelNum = -1;

    public Arena(int width, int height) {
        this();
        this.width = width;
        this.height = height;
    }

    public Arena() {
        this.boulders = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.collisionWalls = new ArrayList<>();
        this.targets = new ArrayList<>();
    }

    public Arena(int width, int height, int levelNum) {
        this(width, height);
        this.levelNum = levelNum;
    }

    public void setDozer(Dozer dozer) {
        this.dozer = dozer;
    }

    public void setBoulders(List<Boulder> boulders) {
        this.boulders = boulders;
    }

    public void setCollisionWalls(List<Wall> collisionWalls) {
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

    public boolean isWall(Position position) {
        for (Wall importantWall : collisionWalls)
            if (importantWall.getPosition().equals(position))
                return true;
        return false;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public List<Wall> getCollisionWalls() {
        return collisionWalls;
    }

    public List<Boulder> getBoulders() {
        return boulders;
    }

    public Dozer getDozer() {
        return dozer;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }
}
