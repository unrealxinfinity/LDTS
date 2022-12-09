package pt.up.fe.edu.dozer.state;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.viewer.Viewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;
    private boolean is_editor=false;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
      this.controller=getController();
    }
    public State(T model,boolean is_editor){
        this.model = model;
        this.viewer = getViewer();
        this.controller=getController();
        this.is_editor=is_editor;
    }
    public boolean get_is_editor(){
        return is_editor;
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(MainGame game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui,time);
    }

}
