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

    public long getInitialTime(){
        return initialTime;
    }
    
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

    public MainGame(State state, AudioManager audio) throws FontFormatException, IOException, URISyntaxException, UnsupportedAudioFileException, LineUnavailableException {
        this.state = state;
        this.BGM = audio;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException {
        try{
            new MainGame(new MenuState(new MainMenu()),new AudioManager("/audio/initSound.wav")).start();
        } catch (InterruptedException e) {
            System.out.println("Program interrupted");
        }
    }

    public void start() throws IOException, FontFormatException, URISyntaxException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        BGM.loopSound();

        LanternaGUI gui = new LanternaGUI(20, 15);
        int frameTime = 25;
        while (this.state != null) {
            long startTime = System.currentTimeMillis();
            long elapsedTime = System.currentTimeMillis() - startTime;
            state.step(this, gui, (startTime - initialTime) / 1000);
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);

            this.getBGM();//usado meramente para os testes
        }
        gui.close();
    }
}
