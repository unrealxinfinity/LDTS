package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;

import java.io.IOException;

public class BoulderController extends GameController {
    private final TargetController targetController;

    public BoulderController(Arena arena, TargetController targetController) {
        super(arena);
        this.targetController = targetController;
    }

    private boolean moveBoulder(Position p, Boulder boulder) {
        if (!getModel().isWall(p) && !getModel().isBoulder(p)) {
            boulder.setPosition(p);
            notifyObserver();
            return true;
        } else return false;
    }

    private Boulder findBoulder(Position p) {
        for (Boulder boulder : getModel().getBoulders())
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

    @Override
    public void step(MainGame game, GUI.ACTION action) throws IOException {

    }

    private void notifyObserver() {
        targetController.update();
    }
}
