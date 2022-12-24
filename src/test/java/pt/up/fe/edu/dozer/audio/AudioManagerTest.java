package pt.up.fe.edu.dozer.audio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class AudioManagerTest {
    AudioManager behaviourCheck;
    AudioManager valuesCheck;

    @BeforeEach
    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        behaviourCheck = Mockito.mock(AudioManager.class);
        valuesCheck = new AudioManager("/audio/applause.wav");
    }

    @Test
    public void playTest() {
        valuesCheck.play();
    }

    @Test
    public void restartAudioTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        try {
            valuesCheck.restartAudio();
        } catch (UnsupportedAudioFileException e) {
            System.out.print("Audio file isn't supported by your program");
            throw new UnsupportedAudioFileException();
        } catch (LineUnavailableException e) {
            System.out.print("A LineUnavailableException is an exception indicating that a line cannot be opened because it is unavailable. This situation arises most commonly when a requested line is already in use by another application,in this case the line in the buffer of the clip.");
            throw new LineUnavailableException();
        } catch (IOException e) {
            System.out.print("IOException");
            throw new IOException();
        }
    }
}
