package pt.up.fe.edu.dozer.state.menu;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menu.LevelSelectController;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class LevelSelectState extends State<LevelSelect> {
    public LevelSelectState(LevelSelect menu) {
        super(menu);
        getModel().nextEntry();
    }

    @Override
    protected Viewer<LevelSelect> getViewer() {
        return new MenuViewer<>(getModel());
    }

    @Override
    protected Controller<LevelSelect> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new LevelSelectController(getModel());
    }

}
