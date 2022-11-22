package pt.up.fe.edu.hero.controller;

import pt.up.fe.edu.hero.MainGame;
import pt.up.fe.edu.hero.gui.GUI;

import java.io.IOException;
import java.sql.Time;

public abstract class Controller<T> {
    private final T model;
    public Controller(T model){
        this.model=model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(MainGame game, GUI gui, long time) throws IOException;
}
