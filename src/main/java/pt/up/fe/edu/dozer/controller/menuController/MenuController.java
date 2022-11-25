package pt.up.fe.edu.dozer.controller.menuController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.menu.Menu;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu){super(menu);}
    @Override
    public void step(MainGame game, GUI gui, long time) throws IOException{
    }
}
