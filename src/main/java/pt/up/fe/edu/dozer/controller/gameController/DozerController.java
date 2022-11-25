package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;

import java.io.IOException;

public class DozerController extends GameController{
    public DozerController(Arena arena){super(arena);}
    private void moveDozer(Position p){
        if(getModel().isWall(p)){
            getModel().getDozer().setPosition(p);
        }
    }
    public void moveDozerLeft() {
        moveDozer(new Position(getModel().getDozer().getPosition().getX()-1 , getModel().getDozer().getPosition().getY()));
    }
    public void moveDozerRight(){
        moveDozer(new Position(getModel().getDozer().getPosition().getX()+1 , getModel().getDozer().getPosition().getY()));
    }
    public void moveDozerUP(){
        moveDozer(new Position(getModel().getDozer().getPosition().getX(),getModel().getDozer().getPosition().getY()-1));
    }
    public void moveDozerDown(){
        moveDozer(new Position(getModel().getDozer().getPosition().getX(),getModel().getDozer().getPosition().getY()+1));
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
