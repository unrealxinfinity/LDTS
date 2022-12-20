package pt.up.fe.edu.dozer;

import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class MainGame {
    private State state;
    private long initialTime = System.currentTimeMillis();
    private final AudioManager BGM;
    private boolean bgmMuted;


    public void setState(State s) {
        state = s;
    }

    public void resetTimer() {
        initialTime = System.currentTimeMillis();
    }

    public void muteBGM() {
        BGM.mute();
        bgmMuted = true;
    }

    public void resumeBGM() {
        BGM.loopSound();
        bgmMuted = false;
    }

    public boolean isBgmMuted() {
        return bgmMuted;
    }

    public AudioManager getBGM() {
        return BGM;
    }

    public MainGame() throws FontFormatException, IOException, URISyntaxException, UnsupportedAudioFileException, LineUnavailableException {
        this.state = new MenuState(new MainMenu());
        this.BGM = new AudioManager("/audio/initSound.wav");
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        new MainGame().start();
    }

    public void start() throws IOException, FontFormatException, URISyntaxException, UnsupportedAudioFileException, LineUnavailableException {

        //audio test -OK
        //AudioManager valuescheck=new AudioManager("/audio/monkeyApplause.wav");
        //valuescheck.play();
        BGM.loopSound();

        LanternaGUI gui = new LanternaGUI(20, 15);
        int frameTime = 25;
        while (this.state != null) {
            long startTime = System.currentTimeMillis();
            long elapsedTime = System.currentTimeMillis() - startTime;
            state.step(this, gui, (startTime - initialTime) / 1000);
            long sleepTime = frameTime - elapsedTime;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }
        gui.close();
    }
}
