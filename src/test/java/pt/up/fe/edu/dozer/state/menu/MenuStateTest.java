package pt.up.fe.edu.dozer.state.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

public class MenuStateTest  {
    @Test
    public void getViewerMain() {
        MenuState state = new MenuState(new MainMenu());

        Assertions.assertNotNull(state.getViewer());
    }

    @Test
    public void getControllerMain() {
        MenuState state = new MenuState(new MainMenu());

        Assertions.assertNotNull(state.getController());
    }

    @Test
    public void getViewerSelect() {
        LevelSelectState state = new LevelSelectState(new LevelSelect());

        Assertions.assertNotNull(state.getViewer());
    }

    @Test
    public void getControllerSelect() {
        LevelSelectState state = new LevelSelectState(new LevelSelect());

        Assertions.assertNotNull(state.getController());
    }
}
