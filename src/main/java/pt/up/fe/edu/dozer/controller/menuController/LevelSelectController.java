package pt.up.fe.edu.dozer.controller.menuController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.MenuState.MenuState;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import java.io.IOException;

public class LevelSelectController extends Controller<Menu> {
    public LevelSelectController(LevelSelect menu){super(menu);}
    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().getCurrentEntry() == "Start") ;//algo para adicionar;
                if (getModel().getCurrentEntry() == "Back") game.setState(new MenuState(new MainMenu()));
                break;

            case LEFT:
                if(getModel().isSelected(0) || getModel().isSelected(1))
                getModel().decrementCurrentDigit();
                break;
            case RIGHT:
                if(getModel().isSelected(0) || getModel().isSelected(1))
                getModel().incrementCurrentDigit();
                break;

        }
    }
}