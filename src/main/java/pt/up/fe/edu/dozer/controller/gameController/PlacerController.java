package pt.up.fe.edu.dozer.controller.gameController;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

import java.io.IOException;

public class PlacerController extends EditorController{

    public PlacerController(EditorArena arena) {
        super(arena);
    }

    private void movePlacer(Position position) {
        Position placerPosition = getModel().getPlacer().getPosition();
        boolean vertical = placerPosition.getY() >= 0 && placerPosition.getY() < getModel().getHeight();
        boolean horizontal = placerPosition.getX() >= 0 && placerPosition.getX() < getModel().getWidth();
        if (vertical && horizontal) getModel().getPlacer().setPosition(position);
    }
    public void movePlacerLeft() {
        movePlacer(getModel().getDozer().getPosition().moveLeft());
    }
    public void movePlacerRight(){
        movePlacer(getModel().getDozer().getPosition().moveRight());
    }
    public void movePlacerUp(){
        movePlacer(getModel().getDozer().getPosition().moveUp());
    }
    public void movePlacerDown(){
        movePlacer(getModel().getDozer().getPosition().moveDown());
    }

    @Override
    public void step(MainGame game, GUI.ACTION action, long time) throws IOException {
        switch (action){
            case UP -> movePlacerUp();
            case DOWN -> movePlacerDown();
            case LEFT -> movePlacerLeft();
            case RIGHT -> movePlacerRight();
        }
    }
}
