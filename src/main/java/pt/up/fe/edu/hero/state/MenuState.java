package pt.up.fe.edu.hero.state;

import pt.up.fe.edu.hero.controller.Controller;
import pt.up.fe.edu.hero.model.menu.Menu;
import pt.up.fe.edu.hero.viewer.Viewer;
import pt.up.fe.edu.hero.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu menu){super(menu);}
    @Override
    public Viewer<Menu> getViewer(){
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return null;
    }
}
