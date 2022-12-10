package pt.up.fe.edu.dozer.viewer;

import pt.up.fe.edu.dozer.gui.GUI;

import java.io.IOException;

public abstract class Viewer<T> {
    private final T model;

    public Viewer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void draw(GUI gui,long time) throws IOException {
        gui.clear();
        drawElements(gui, time);
        gui.refresh();
    }

    protected abstract void drawElements(GUI gui, long time);
}
