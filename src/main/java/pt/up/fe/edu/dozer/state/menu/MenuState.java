package pt.up.fe.edu.dozer.state.menu;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menu.MenuController;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class MenuState extends State<MainMenu> {
    public MenuState(MainMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(menu);

    }

    @Override
    protected Viewer<MainMenu> getViewer() {
        return new MenuViewer<>(getModel());
    }

    @Override
    protected Controller<MainMenu> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new MenuController(getModel());
    }

}
