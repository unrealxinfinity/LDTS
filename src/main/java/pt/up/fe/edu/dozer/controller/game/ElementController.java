package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.model.game.arena.Arena;

public abstract class ElementController {
    private final Arena model;

    public ElementController(Arena arena) {
        this.model = arena;
    }

    public Arena getModel() {
        return this.model;
    }
}
