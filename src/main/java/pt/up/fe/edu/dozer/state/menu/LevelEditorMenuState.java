package pt.up.fe.edu.dozer.state.menu;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menu.LevelEditorMenuController;
import pt.up.fe.edu.dozer.controller.menu.LevelSelectController;
import pt.up.fe.edu.dozer.model.menu.LevelEditorMenu;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

public class LevelEditorMenuState extends State<Menu> {
    public LevelEditorMenuState(LevelEditorMenu menu){
        super(menu);
        getModel().nextEntry();
    }
    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new LevelEditorMenuController( (LevelEditorMenu) getModel());
    }
}
