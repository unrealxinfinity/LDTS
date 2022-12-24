package pt.up.fe.edu.dozer.state.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MenuStateTest {
    @Test
    public void getViewerMain() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MenuState state = new MenuState(new MainMenu());

        Assertions.assertNotNull(state.getViewer());
    }

    @Test
    public void getControllerMain() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MenuState state = new MenuState(new MainMenu());

        Assertions.assertNotNull(state.getController());
    }

    @Test
    public void getViewerSelect() {
        LevelSelectState state = new LevelSelectState(new LevelSelect());

        Assertions.assertNotNull(state.getViewer());
    }

    @Test
    public void getControllerSelect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        LevelSelectState state = new LevelSelectState(new LevelSelect());

        Assertions.assertNotNull(state.getController());
    }
}
