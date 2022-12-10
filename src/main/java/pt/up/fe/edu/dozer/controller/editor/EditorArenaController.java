package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.menu.LevelEditorMenu;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.editor.DozerEditorState;
import pt.up.fe.edu.dozer.state.editor.EditorState;
import pt.up.fe.edu.dozer.state.menu.LevelEditorMenuState;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class EditorArenaController extends EditorController{
    private final PlacerController controller;


    public EditorArenaController(EditorArena arena) {
        super(arena);
        this.controller = new PlacerController(arena);

    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.CYCLE) {
            game.setState(cycleState());
        }
        else if (action == GUI.ACTION.SELECT) {
            clearPosition();
            placeElement();
        }
        else if (action == GUI.ACTION.REMOVE) {
            clearPosition();
        }
        else if(action == GUI.ACTION.RESTART){
            game.setState(new DozerEditorState(new EditorArena(20,12)));
        }
        else if(action == GUI.ACTION.PAUSE){
            game.resetTimer();
            try {
                game.setState(new MenuState(new MainMenu()));
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
        else if(action == GUI.ACTION.SAVE) {
            if (getModel().getDozer() != null) {
                game.resetTimer();
                //game.setState(new LevelEditorMenuState(new LevelEditorMenu()));
                game.setState(new GameState(getModel().getArena()));
            }
            game.setState(new LevelEditorMenuState(new LevelEditorMenu()));

        }
        else if(action== GUI.ACTION.MUTE){
            if(game.isBgmMuted()){
                game.resumeBGM();
            }
            else game.muteBGM();
        }
        else this.controller.step(game, action, time);
    }

    protected abstract EditorState cycleState();
    protected abstract void placeElement();

    private void clearPosition() {
        EditorPositionClearer clearer = new EditorPositionClearer(getModel());
        clearer.clearPosition(getModel().getPlacer().getPosition());
    }
}
