package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.ArenaBuilder;
import pt.up.fe.edu.dozer.model.game.arena.LevelReader;
import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.menu.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ArenaController extends GameController {
    private final DozerController dozerController;
    private final BoulderController boulderController;
    private final TargetController targetController;
    private final int numTargets;

    public ArenaController(Arena arena) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(arena, new AudioManager("/audio/monkeyApplause.wav"));
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
            restartArena(game);
        } else if (this.numTargets == this.targetController.getBouldersInTargets()) {
            try {
                ArenaBuilder builder = new LoaderArenaBuilder(getModel().getLevelNum() + 1, new LevelReader());
                game.resetTimer();
                getSound().play();
                game.setState(new GameState(builder.createArena(new Arena())));
            } catch (NullPointerException e) {
                game.resetTimer();
                game.setState(new MenuState(new MainMenu()));

            }

        } else this.dozerController.step(game, action, time);
    }

    protected void restartArena(MainGame game) throws IOException {
        ArenaBuilder builder = new LoaderArenaBuilder(getModel().getLevelNum(), new LevelReader());
        game.resetTimer();
        game.setState(new GameState(builder.createArena(new Arena())));
    }
}
