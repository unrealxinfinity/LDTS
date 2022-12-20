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

public class MenuController extends Controller<MainMenu> {
    public MenuController(MainMenu menu,AudioManager audio) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(menu,audio);
    }

    @Override
    public void step(MainGame game, GUI.ACTION action) throws IOException {
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
                    break;
                case MUTE:
                    if(game.isBgmMuted()) game.resumeBGM();
                    else game.muteBGM();
                    break;
                default:
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

