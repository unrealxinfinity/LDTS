package pt.up.fe.edu.dozer.controller;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;

import java.io.IOException;

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
