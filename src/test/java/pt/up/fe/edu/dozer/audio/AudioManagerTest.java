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
        behaviourCheck= Mockito.mock(AudioManager.class);
        valuesCheck=new AudioManager("/audio/applause.wav");
    }
    @Test
    public void playTest(){
        valuesCheck.play();
    }
}
