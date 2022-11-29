package pt.up.fe.edu.dozer.state.MenuState;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menuController.LevelSelectController;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

public class LevelSelectState extends State<Menu> {
    public LevelSelectState(LevelSelect menu){super(menu);}

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(new LevelSelect());
    }

    @Override
    protected Controller<Menu> getController() {
        return new LevelSelectController(new LevelSelect());
    }

}
