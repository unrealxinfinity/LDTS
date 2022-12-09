package pt.up.fe.edu.dozer.controller.game;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.audio.AudioManager;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class DozerController extends GameController{
    private BoulderController boulderController;
    private AudioManager movingSound;

    public enum directions {UP, DOWN, LEFT, RIGHT};
    private directions direction;
    public DozerController(Arena arena, BoulderController boulderController){
        super(arena);
        this.boulderController = boulderController;
        try{
            movingSound= new AudioManager("/audio/jumpCoin.wav");
        }catch (UnsupportedAudioFileException e) {
            System.out.print("Audio file format not supported for dozerController");
        } catch (LineUnavailableException e) {
            System.out.print("Audio already in use in dozerController");
        } catch (IOException e) {
            System.out.print("Audio not found");
        }
    }
    private void moveDozer(Position p){
        if (getModel().isBoulder(p)) {
            boolean hasMoved = false;
            switch (direction) {
                case UP -> {hasMoved = boulderController.moveBoulderUp(p);}
                case LEFT -> {hasMoved = boulderController.moveBoulderLeft(p);}
                case RIGHT -> {hasMoved = boulderController.moveBoulderRight(p);}
                case DOWN -> {hasMoved = boulderController.moveBoulderDown(p);}
            }
            if (hasMoved) getModel().getDozer().setPosition(p);
        }
        else if(!getModel().isWall(p)){
            getModel().getDozer().setPosition(p);
        }
    }
    public void moveDozerLeft() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.direction = directions.LEFT;
        moveDozer(getModel().getDozer().getPosition().moveLeft());

        movingSound.play();
    }
    public void moveDozerRight() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.direction = directions.RIGHT;
        moveDozer(getModel().getDozer().getPosition().moveRight());
        movingSound.play();
    }
    public void moveDozerUP() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.direction = directions.UP;
        moveDozer(getModel().getDozer().getPosition().moveUp());
        movingSound.play();
    }
    public void moveDozerDown() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.direction = directions.DOWN;
        moveDozer(getModel().getDozer().getPosition().moveDown());
        movingSound.play();
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) {
       try {
           switch (action) {
               case UP -> moveDozerUP();
               case DOWN -> moveDozerDown();
               case LEFT -> moveDozerLeft();
               case RIGHT -> moveDozerRight();
           }
       }catch (UnsupportedAudioFileException e) {
           System.out.print("Audio file format not supported for dozerController");
       } catch (LineUnavailableException e) {
           System.out.print("Audio already in use in dozerController");
       } catch (IOException e) {
           System.out.print("Audio not found");
       }
    }

}
