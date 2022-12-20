package pt.up.fe.edu.dozer.controller.menu;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.LevelReader;
import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class LevelSelectController extends GenericMenuController<LevelSelect> {
    public LevelSelectController(LevelSelect menu,AudioManager audio) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(menu,audio);
    }

    @Override
    protected void stepLeft() {
        if (getModel().isSelectedNumber())
            getModel().decrementCurrentDigit();
    }

    @Override
    protected void stepRight() {
        if (getModel().isSelectedNumber())
            getModel().incrementCurrentDigit();
    }

    @Override
    protected void stepSelect(MainGame game) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (getModel().isSelectedStart()) {
            try {
                int selectedLevel = getModel().getSelectedLevel();
                Arena arena = new LoaderArenaBuilder(selectedLevel, new LevelReader()).createArena(new Arena());
                game.resetTimer();
                game.setState(new GameState(arena));
            } catch (NullPointerException | IOException ignored) {
            }
        }
        if (getModel().isSelectedBack()) {
            game.resetTimer();
            game.setState(new MenuState(new MainMenu()));
        }
    }
}