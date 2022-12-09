package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.ArenaBuilder;
import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ArenaController extends GameController{
    private final DozerController dozerController;
    private final BoulderController boulderController;
    private final TargetController targetController;
    private final int numTargets;

    public ArenaController(Arena arena) {
        super(arena);

        this.targetController = new TargetController(arena);
        this.boulderController = new BoulderController(arena, this.targetController);
        this.dozerController = new DozerController(arena, this.boulderController);
        this.numTargets = arena.getTargets().size();
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.PAUSE)
            game.setState(new MenuState(new MainMenu()));
        else if (action == GUI.ACTION.RESTART) {
            ArenaBuilder builder = new LoaderArenaBuilder(getModel().getLevelNum());
            game.setState(new GameState(builder.createArena()));
        }
        else if (this.numTargets == this.targetController.getBouldersInTargets()) {
            try {
                ArenaBuilder builder = new LoaderArenaBuilder(getModel().getLevelNum() + 1);
                AudioManager passLevelAudio= new AudioManager("/audio/monkeyApplause.wav");
                passLevelAudio.play();
                game.setState(new GameState(builder.createArena()));


            }
            catch (NullPointerException ignored) {}
            catch (UnsupportedAudioFileException ignored) {}
            catch (LineUnavailableException ignored) {}
        }
        else this.dozerController.step(game, action, time);
    }
}
