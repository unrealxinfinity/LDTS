package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;

import java.util.List;

public class BoulderController extends ElementController {
    private final BoulderObserver observer;

    public BoulderController(Arena arena, BoulderObserver observer) {
        super(arena);
        this.observer = observer;
    }

    private boolean moveBoulder(Position p, Boulder boulder) {
        if (!getModel().isWall(p) && !getModel().isBoulder(p)) {
            boulder.setPosition(p);
            notifyObserver();
            return true;
        } else return false;
    }

    private Boulder findBoulder(Position p) {
        List<Boulder> boulders = getModel().getBoulders();
        for (Boulder boulder : boulders)
            if (p.equals(boulder.getPosition()))
                return boulder;
        return null;
    }

    public boolean moveBoulderUp(Position p) {
        Boulder boulder = findBoulder(p);
        return moveBoulder(p.moveUp(), boulder);
    }

    public boolean moveBoulderLeft(Position p) {
        Boulder boulder = findBoulder(p);
        return moveBoulder(p.moveLeft(), boulder);
    }

    public boolean moveBoulderRight(Position p) {
        Boulder boulder = findBoulder(p);
        return moveBoulder(p.moveRight(), boulder);
    }

    public boolean moveBoulderDown(Position p) {
        Boulder boulder = findBoulder(p);
        return moveBoulder(p.moveDown(), boulder);
    }

    private void notifyObserver() {
        observer.update();
    }
}
