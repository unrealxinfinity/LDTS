package pt.up.fe.edu.dozer.controller.menu;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.state.editor.DozerEditorState;
import pt.up.fe.edu.dozer.state.menu.LevelSelectState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(MainMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(menu, new AudioManager("/audio/menu.wav"));
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        try {
            switch (action) {
                case UP:
                    getSound().restartAudio();
                    getSound().play();
                    getModel().previousEntry();
                    break;
                case DOWN:
                    getSound().restartAudio();
                    getSound().play();
                    getModel().nextEntry();
                    break;
                case SELECT:
                    getSound().restartAudio();
                    getSound().play();
                    if (getModel().isSelected(2)) game.setState(null);
                    if (getModel().isSelected(0)) {
                        game.resetTimer();
                        game.setState(new LevelSelectState(new LevelSelect()));
                    }
                    if (getModel().isSelected(1)) {
                        game.resetTimer();
                        game.setState(new DozerEditorState(new EditorArena(20, 12)));
                    }

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

