package pt.up.fe.edu.dozer.controller.menu;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import java.io.IOException;

public class LevelSelectController extends Controller<Menu> {
    public LevelSelectController(LevelSelect menu){super(menu);}
    @Override
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
                if (getModel().getCurrentEntry() == "Start") {
                    try {
                        int selectedLevel = ((LevelSelect) getModel()).getSelectedLevel();
                        Arena arena = new LoaderArenaBuilder(selectedLevel).createArena();
                        game.setState(new GameState(arena));
                    } catch (NullPointerException ignored) {}
                }
                if (getModel().getCurrentEntry() == "Back") game.setState(new MenuState(new MainMenu()));
                break;

            case LEFT:
                if(getModel().isSelected(0) || getModel().isSelected(1))
                    ((LevelSelect)getModel()).decrementCurrentDigit();
                break;
            case RIGHT:
                if(getModel().isSelected(0) || getModel().isSelected(1))
                    ((LevelSelect)getModel()).incrementCurrentDigit();
                break;

        }
    }
}