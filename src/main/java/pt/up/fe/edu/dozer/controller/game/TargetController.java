package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Boulder;
import pt.up.fe.edu.dozer.model.game.elements.Target;

import java.util.List;

public class TargetController extends ElementController implements BoulderObserver {
    private int bouldersInTargets = 0;

    public TargetController(Arena arena) {
        super(arena);
    }

    @Override
    public void update() {
        this.bouldersInTargets = 0;
        List<Target> targets = getModel().getTargets();
        for (Target target : targets) {
            List<Boulder> boulders = getModel().getBoulders();
            for (Boulder boulder : boulders) {
                boolean equals = target.getPosition().equals(boulder.getPosition());
                if (equals) bouldersInTargets++;
            }
        }
    }

    public int getBouldersInTargets() {
        return this.bouldersInTargets;
    }
}
