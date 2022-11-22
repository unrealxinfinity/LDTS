package pt.up.fe.edu.hero.controller.gameController;

import pt.up.fe.edu.hero.MainGame;
import pt.up.fe.edu.hero.controller.Controller;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.game.arena.Arena;

import java.io.IOException;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena){super(arena);}



}
