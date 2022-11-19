package pt.up.fe.edu.hero.gui;

import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class LanternaGUITest {
    private Screen setUpTest() {
        return Mockito.mock(Screen.class);
    }

    @Test
    public void closeTest() throws IOException {
        Screen screen = setUpTest();
        LanternaGUI gui = new LanternaGUI(screen);

        gui.close();

        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    public void refreshTest() throws IOException {
        Screen screen = setUpTest();
        LanternaGUI gui = new LanternaGUI(screen);

        gui.refresh();

        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    public void clearTest() throws IOException {
        Screen screen = setUpTest();
        LanternaGUI gui = new LanternaGUI(screen);

        gui.clear();

        Mockito.verify(screen, Mockito.times(1)).clear();
    }
}
