package pt.up.fe.edu.dozer.controller.menuController;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.MenuState.MenuState;


import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu){super(menu);}
    @Override
    public void step(MainGame game, GUI gui, long time) throws IOException{
        switch (gui.getNextAction()){
            case UP : getModel().previousEntry();
                break;
            case DOWN : getModel().nextEntry();
                break;
            case SELECT :
                if(getModel().getCurrentEntry()=="Quit") game.setState(null);
                if(getModel().getCurrentEntry()=="Level Select") game.setState(new MenuState(new LevelSelect()));
                if(getModel().getCurrentEntry()=="Level Editor") ;
                if(getModel().getCurrentEntry()=="Start")//algo para adicionar;
                if(getModel().getCurrentEntry()=="Back") game.setState(new MenuState(new MainMenu()));


        }
    }
}
