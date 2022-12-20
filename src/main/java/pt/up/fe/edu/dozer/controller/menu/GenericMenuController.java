package pt.up.fe.edu.dozer.controller.menu;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.menu.Menu;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class GenericMenuController<T extends Menu> extends Controller<T> {
    public GenericMenuController(T model, AudioManager audio) {
        super(model, audio);
    }

    @Override
    public void step(MainGame game, GUI.ACTION action) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        switch (action) {
            case UP:
                playSound();
                stepUp();
                break;
            case DOWN:
                playSound();
                stepDown();
                break;
            case LEFT:
                playSound();
                stepLeft();
                break;
            case RIGHT:
                playSound();
                stepRight();
                break;
            case SELECT:
                playSound();
                stepSelect(game);
                break;
            case MUTE:
                if(game.isBgmMuted()) game.resumeBGM();
                else game.muteBGM();
                break;
            default:
                break;
        }
    }

    private void stepUp() {
        getModel().previousEntry();
    }

    private void stepDown() {
        getModel().nextEntry();
    }

    protected abstract void stepLeft();
    protected abstract void stepRight();

    protected abstract void stepSelect(MainGame game) throws UnsupportedAudioFileException, LineUnavailableException, IOException;

    private void playSound() throws IOException {
        try {
            getSound().restartAudio();
            getSound().play();
        } catch (UnsupportedAudioFileException e) {
            System.out.print("File not Supported");
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            System.out.print("Audio in use");
            throw new RuntimeException(e);
        }
    }
}
