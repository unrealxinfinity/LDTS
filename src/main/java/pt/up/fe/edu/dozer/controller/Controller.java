package pt.up.fe.edu.dozer.controller;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.gui.GUI;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class Controller<T> {
    private final T model;
    private AudioManager sound;

    public Controller(T model) {
        this.model = model;
    }

    public Controller(T model, AudioManager audio) {
        this.model = model;
        this.sound = audio;

    }

    public AudioManager getSound() {
        return sound;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(MainGame game, GUI.ACTION action) throws IOException, UnsupportedAudioFileException, LineUnavailableException;

}
