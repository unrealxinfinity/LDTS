package pt.up.fe.edu.dozer.state.MenuState;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menuController.MenuController;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;


public class MenuState extends State<Menu> {
    public MenuState(MainMenu menu){super(menu);}

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer( getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController((MainMenu) getModel());
    }

}
