package pt.up.fe.edu.dozer.controller.menu;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;
import pt.up.fe.edu.dozer.model.menu.LevelEditorMenu;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import java.io.IOException;

public class LevelEditorMenuController extends Controller<Menu> {
    public LevelEditorMenuController(LevelEditorMenu menu){
        super(menu);
    }
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                if(getModel().isSelected(0)) getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                if(getModel().isSelected(0)) getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().getSelectedNum()==2) {
                    try {
                        //Something to implement to save the levels
                    } catch (NullPointerException ignored) {

                    }
                }
                break;

            case LEFT:
                if( getModel().isSelected(1))
                    ((LevelSelect)getModel()).decrementCurrentDigit();
                break;
            case RIGHT:
                if(getModel().isSelected(1))
                    ((LevelSelect)getModel()).incrementCurrentDigit();
                break;
            case PAUSE:
                game.resetTimer();
                game.setState(new MenuState(new MainMenu()));
                break;
        }
    }

}
