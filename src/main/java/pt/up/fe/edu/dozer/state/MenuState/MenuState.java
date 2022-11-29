package pt.up.fe.edu.dozer.state.MenuState;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menuController.MenuController;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;


public abstract class MenuState<T extends Menu> extends State<Menu> {
    public MenuState(Menu menu){super(menu);}
    @Override
    public Viewer<Menu> getViewer(){
        return new MenuViewer(getModel());
    }

    abstract protected Controller<Menu> getController();

}
