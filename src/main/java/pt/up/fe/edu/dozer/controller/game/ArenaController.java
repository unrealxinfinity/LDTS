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
import java.nio.file.NoSuchFileException;


public class ArenaController extends GameController {

    private final DozerController dozerController;


    private final TargetController targetController;


    private final int numTargets;


    public ArenaController(Arena arena, AudioManager audio) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(arena, audio);
        this.targetController = new TargetController(arena);
        BoulderController boulderController = new BoulderController(arena, this.targetController);
        this.dozerController = new DozerController(arena, boulderController);
        this.numTargets = arena.getTargets().size();
    }

    @Override
    public void step(MainGame game, GUI.ACTION action) throws IOException {
        if (action == GUI.ACTION.PAUSE)
            try {
                dozerController.getSound().close();
                getSound().close();
                game.setState(new MenuState(new MainMenu()));
            } catch (UnsupportedAudioFileException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        else if (action == GUI.ACTION.RESTART) {
            restartArena(game);
        } else if (this.numTargets == this.targetController.getBouldersInTargets()) {
            try {
                ArenaBuilder builder = new LoaderArenaBuilder(getModel().getLevelNum() + 1, new LevelReader());
                game.resetTimer();
                getSound().play();
                Arena arena = builder.createArena(new Arena());
                game.setState(new GameState(arena));
            } catch (NoSuchFileException e) {
                game.resetTimer();
                try {
                    game.setState(new MenuState(new MainMenu()));
                } catch (UnsupportedAudioFileException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            }

        } else if (action == GUI.ACTION.MUTE) {
            if (game.isBgmMuted()) {
                game.resumeBGM();
            } else game.muteBGM();
        } else this.dozerController.step(game, action);

    }

    protected void restartArena(MainGame game) throws IOException {
        ArenaBuilder builder = new LoaderArenaBuilder(getModel().getLevelNum(), new LevelReader());
        game.resetTimer();
        game.setState(new GameState(builder.createArena(new Arena())));
    }
}
