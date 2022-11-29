package pt.up.fe.edu.dozer.state.MenuState;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menuController.LevelSelectController;
import pt.up.fe.edu.dozer.controller.menuController.MenuController;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import java.util.logging.Level;

public class LevelSelectState extends MenuState<LevelSelect> {
    public LevelSelectState(LevelSelect menu){super(menu);}

    @Override
    protected Controller<Menu> getController() {
        return new LevelSelectController((LevelSelect) getModel());
    }

}
