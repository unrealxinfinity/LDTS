package pt.up.fe.edu.dozer.state;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menuController.MenuController;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;


public class MenuState extends State<Menu> {
    public MenuState(Menu menu){super(menu);}
    @Override
    public Viewer<Menu> getViewer(){
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
