package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.model.game.elements.Placer;

public class EditorArena extends Arena{
    public EditorArena(int width, int height) {
        super(width, height);
        this.placer = new Placer(5, 5, new Dozer(5,5));
    }

    public Arena getArena() {
        Arena arena = new Arena(getWidth(), getHeight());
        arena.setDozer(getDozer());
        arena.setCollisionWalls(getCollisionWalls());
        arena.setTargets(getTargets());
        arena.setBoulders(getBoulders());
        return arena;
    }

    public Placer getPlacer() {
        return placer;
    }

    public void setPlacer(Placer placer) {
        this.placer = placer;
    }

    private Placer placer;
}
