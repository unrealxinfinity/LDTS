package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.model.game.elements.Target;

import java.io.IOException;

public class TargetController extends GameController implements BoulderObserver {
    private int bouldersInTargets = 0;

    public TargetController(Arena arena) {
        super(arena);
    }

    @Override
    public void step(MainGame game, GUI.ACTION action) throws IOException {
    }


    @Override
    public void update() {
        this.bouldersInTargets = 0;
        for (Target target : getModel().getTargets())
            for (Boulder boudler : getModel().getBoulders())
                if (target.getPosition().equals(boudler.getPosition())) bouldersInTargets++;
    }

    public int getBouldersInTargets() {
        return this.bouldersInTargets;
    }
}
