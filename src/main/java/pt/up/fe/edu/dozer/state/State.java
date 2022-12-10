package pt.up.fe.edu.dozer.state;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.viewer.Viewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();

        try {
            this.controller = getController();
        } catch (UnsupportedAudioFileException e) {
            System.out.print("File not Supported");
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            System.out.print("Audio in use");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.print("File doenst exist");
            throw new RuntimeException(e);
        }
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException;

    public T getModel() {
        return model;
    }

    public void step(MainGame game, GUI gui, long time) throws IOException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui, time);
    }

}
