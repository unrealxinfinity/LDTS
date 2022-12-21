package pt.up.fe.edu.dozer.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioManager {
    private AudioInputStream soundStream;
    private Clip clip;
    private final String filepath;


    public AudioManager(String soundFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        filepath = soundFilePath;
        URL resource = AudioManager.class.getResource(soundFilePath);
        soundStream = AudioSystem.getAudioInputStream(resource);
        DataLine.Info info = new DataLine.Info(Clip.class, soundStream.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(soundStream);
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void restartAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL resource = AudioManager.class.getResource(filepath);
        soundStream = AudioSystem.getAudioInputStream(resource);
        DataLine.Info info = new DataLine.Info(Clip.class, soundStream.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(soundStream);
    }

    public void loopSound() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void mute() {
        clip.setFramePosition(0);
        clip.stop();
    }

    public void close() {
        clip.close();
    }
}
