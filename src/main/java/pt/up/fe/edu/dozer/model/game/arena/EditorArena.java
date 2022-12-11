package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.game.elements.*;

import java.util.ArrayList;
import java.util.List;

public class EditorArena extends Arena {
    public EditorArena(int width, int height) {
        super(width, height);
        this.placer = new Placer(5, 5, new Dozer(5, 5));
    }

    public EditorArena getArena() {
        EditorArena arena = new EditorArena(getWidth(), getHeight());
        arena.setDozer(new Dozer(getDozer().getPosition().getX(), getDozer().getPosition().getY()));

        List<Wall> walls = new ArrayList<>();
        for (Wall wall : getCollisionWalls())
            walls.add(new ImportantWall(wall.getPosition().getX(), wall.getPosition().getY()));
        arena.setCollisionWalls(walls);

        List<Target> targets = new ArrayList<>();
        for (Target target : getTargets())
            targets.add(new Target(target.getPosition().getX(), target.getPosition().getY()));
        arena.setTargets(targets);

        List<Boulder> boulders = new ArrayList<>();
        for (Boulder boulder : getBoulders())
            boulders.add(new Boulder(boulder.getPosition().getX(), boulder.getPosition().getY()));
        arena.setBoulders(boulders);

        return arena;
    }

    public Placer getPlacer() {
        return placer;
    }

    public void setPlacer(Placer placer) {
        this .placer = placer;
    }

    private Placer placer;
}
