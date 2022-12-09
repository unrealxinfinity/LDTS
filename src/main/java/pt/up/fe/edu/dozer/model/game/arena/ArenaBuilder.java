package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.model.game.elements.Target;
import pt.up.fe.edu.dozer.model.game.elements.Wall;

import java.util.List;

public abstract class ArenaBuilder {
    public Arena createArena(Arena arena) {
        arena.setHeight(getHeight());
        arena.setWidth(getWidth());
        arena.setLevelNum(getLevelNum());

        arena.setDozer(createDozer());
        arena.setWalls(createWalls());
        arena.setCollisionWalls(createCollisionWalls());
        arena.setBoulders(createBoulders());
        arena.setTargets(createTargets());

        return arena;
    }

    protected abstract int getWidth();
    protected abstract int getHeight();
    protected abstract Dozer createDozer();
    protected abstract List<Wall> createWalls();
    protected abstract List<Wall> createCollisionWalls();
    protected abstract List<Boulder> createBoulders();
    protected abstract List<Target> createTargets();
    protected int getLevelNum() {
        return -1;
    }
}
