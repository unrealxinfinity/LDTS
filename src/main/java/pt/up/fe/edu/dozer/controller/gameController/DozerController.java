package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

import java.io.IOException;

public class DozerController extends GameController{
    private BoulderController boulderController;
    public enum directions {UP, DOWN, LEFT, RIGHT};
    private directions direction;
    public DozerController(Arena arena, BoulderController boulderController){
        super(arena);
        this.boulderController = boulderController;
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
    public void moveDozerLeft() {
        this.direction = directions.LEFT;
        moveDozer(getModel().getDozer().getPosition().moveLeft());
    }
    public void moveDozerRight(){
        this.direction = directions.RIGHT;
        moveDozer(getModel().getDozer().getPosition().moveRight());
    }
    public void moveDozerUP(){
        this.direction = directions.UP;
        moveDozer(getModel().getDozer().getPosition().moveUp());
    }
    public void moveDozerDown(){
        this.direction = directions.DOWN;
        moveDozer(getModel().getDozer().getPosition().moveDown());
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) {
        switch (action){
            case UP -> moveDozerUP();
            case DOWN -> moveDozerDown();
            case LEFT -> moveDozerLeft();
            case RIGHT -> moveDozerRight();
        }
    }
}
