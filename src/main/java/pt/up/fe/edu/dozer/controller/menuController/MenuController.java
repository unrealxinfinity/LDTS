package pt.up.fe.edu.dozer.controller.menuController;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.DozerEditorState;
import pt.up.fe.edu.dozer.state.EditorState;
import pt.up.fe.edu.dozer.state.MenuState.LevelSelectState;
import pt.up.fe.edu.dozer.state.MenuState.MenuState;


import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(MainMenu menu){super(menu);}
    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException{
        switch (action){
            case UP : getModel().previousEntry();
                break;
            case DOWN : getModel().nextEntry();
                break;
            case SELECT :
                if (getModel().isSelected(2)) game.setState(null);
                if(getModel().isSelected(0)) game.setState(new LevelSelectState(new LevelSelect()));
                if(getModel().isSelected(1)) game.setState(new DozerEditorState(new EditorArena(20,12)));


        }
    }
}
