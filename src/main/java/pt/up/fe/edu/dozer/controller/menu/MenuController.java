package pt.up.fe.edu.dozer.controller.menu;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.editor.DozerEditorState;
import pt.up.fe.edu.dozer.state.menu.LevelSelectState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MenuController extends GenericMenuController<MainMenu> {
    public MenuController(MainMenu menu,AudioManager audio) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
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
            game.getBGM().close();
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

