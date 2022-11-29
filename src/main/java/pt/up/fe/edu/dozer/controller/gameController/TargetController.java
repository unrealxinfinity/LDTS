package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

import java.io.IOException;

public class TargetController extends GameController implements BoulderObserver{
    public TargetController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        
    }


    @Override
    public void update() {

    }
}
