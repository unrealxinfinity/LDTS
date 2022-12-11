package pt.up.fe.edu.dozer.state.menu;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menu.LevelEditorMenuController;
import pt.up.fe.edu.dozer.model.menu.LevelEditorMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class LevelEditorMenuState extends State<Menu> {
    public LevelEditorMenuState(LevelEditorMenu menu) {
        super(menu);
        getModel().nextEntry();
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new LevelEditorMenuController((LevelEditorMenu) getModel());
    }
}