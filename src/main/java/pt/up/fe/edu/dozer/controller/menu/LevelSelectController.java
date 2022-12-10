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
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Objects;

public class LevelSelectController extends Controller<Menu> {
    public LevelSelectController(LevelSelect menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {super(menu,new AudioManager("/audio/menu.wav"));}
    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        try {
            switch (action) {
                case UP:
                    getSound().restartAudio();
                    getSound().play();
                    getModel().previousEntry();
                    if (getModel().isSelected(0)) getModel().previousEntry();
                    break;
                case DOWN:
                    getSound().restartAudio();
                    getSound().play();
                    getModel().nextEntry();
                    if (getModel().isSelected(0)) getModel().nextEntry();
                    break;
                case SELECT:
                    getSound().restartAudio();
                    getSound().play();
                    if (Objects.equals(getModel().getCurrentEntry(), "Start")) {
                        try {
                            int selectedLevel = ((LevelSelect) getModel()).getSelectedLevel();
                            Arena arena = new LoaderArenaBuilder(selectedLevel, new LevelReader()).createArena(new Arena());
                            game.resetTimer();
                            game.setState(new GameState(arena));
                        } catch (NullPointerException ignored) {
                            System.out.print("No correspondent level");
                        }
                    }
                    if (Objects.equals(getModel().getCurrentEntry(), "Back")) {
                        game.resetTimer();
                        game.setState(new MenuState(new MainMenu()));
                    }
                    break;

                case LEFT:
                    getSound().restartAudio();
                    getSound().play();
                    if (getModel().isSelected(1))
                        ((LevelSelect) getModel()).decrementCurrentDigit();
                    break;
                case RIGHT:
                    getSound().restartAudio();
                    getSound().play();
                    if (getModel().isSelected(1))
                        ((LevelSelect) getModel()).incrementCurrentDigit();
                    break;

            }
        } catch (UnsupportedAudioFileException e) {
            System.out.print("File not Supported");
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            System.out.print("Audio in use");
            throw new RuntimeException(e);
        }
    }
}