package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

public class BoulderController extends GameController{
    public BoulderController(Arena arena) {super(arena);}

    private void moveBoulder(Position p) {
        if(!getModel().isWall(p) && !getModel().isBoulder(p)){
            getModel().getDozer().setPosition(p);
        }
    }
}
