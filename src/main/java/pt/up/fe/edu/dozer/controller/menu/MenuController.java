package pt.up.fe.edu.dozer.controller.menu;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.editor.DozerEditorState;
import pt.up.fe.edu.dozer.state.menu.LevelSelectState;

public class MenuController extends GenericMenuController<MainMenu> {
    public MenuController(MainMenu menu,AudioManager audio) {
        super(menu,audio);
    }

    @Override
    protected void stepLeft() {

    }

    @Override
    protected void stepRight() {

    }

    @Override
    protected void stepSelect(MainGame game) {
        if (getModel().isSelectedQuit()) {
            AudioManager bgm = game.getBGM();
            bgm.close();
            getSound().close();
            game.setState(null);
        }
        if (getModel().isSelectedSelect()) {
            game.resetTimer();
            game.setState(new LevelSelectState(new LevelSelect()));
        }
        if (getModel().isSelectedEditor()) {
            game.resetTimer();
            game.setState(new DozerEditorState(new EditorArena(20, 12)));
        }
    }
}

