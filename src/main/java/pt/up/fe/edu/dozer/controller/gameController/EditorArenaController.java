package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

import java.io.IOException;

public class EditorArenaController extends EditorController{
    private final PlacerController controller;

    public EditorArenaController(EditorArena arena) {
        super(arena);
        this.controller = new PlacerController(arena);
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        controller.step(game, action, time);
    }
}
