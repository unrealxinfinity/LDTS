package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

import java.io.IOException;

public class DozerController extends GameController{
    public DozerController(Arena arena){super(arena);}
    private void moveDozer(Position p){
        if(!getModel().isWall(p)){
            getModel().getDozer().setPosition(p);
        }
    }
    public void moveDozerLeft() {
        moveDozer(getModel().getDozer().getPosition().setLeft());
    }
    public void moveDozerRight(){
        moveDozer( getModel().getDozer().getPosition().setRight());
    }
    public void moveDozerUP(){
        moveDozer(getModel().getDozer().getPosition().setUp());
    }
    public void moveDozerDown(){
        moveDozer(getModel().getDozer().getPosition().setDown());
    }

    @Override
    public void step(MainGame game, GUI gui, long time) throws IOException{
        switch (gui.getNextAction()){
            case UP -> moveDozerUP();
            case DOWN -> moveDozerDown();
            case LEFT -> moveDozerLeft();
            case RIGHT -> moveDozerRight();
        }
    }
}
