package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;

import java.io.IOException;

public class PlacerController extends EditorController {

    public PlacerController(EditorArena arena) {
        super(arena);
    }

    private void movePlacer(Position position) {
        boolean vertical = position.getY() >= 0 && position.getY() < getModel().getHeight();
        boolean horizontal = position.getX() >= 0 && position.getX() < getModel().getWidth();
        if (vertical && horizontal) getModel().getPlacer().setPosition(position);
    }

    public void movePlacerLeft() {
        movePlacer(getModel().getPlacer().getPosition().moveLeft());
    }

    public void movePlacerRight() {
        movePlacer(getModel().getPlacer().getPosition().moveRight());
    }

    public void movePlacerUp() {
        movePlacer(getModel().getPlacer().getPosition().moveUp());
    }

    public void movePlacerDown() {
        movePlacer(getModel().getPlacer().getPosition().moveDown());
    }

    @Override
    public void step(MainGame game, GUI.ACTION action) throws IOException {
        switch (action) {
            case UP -> movePlacerUp();
            case DOWN -> movePlacerDown();
            case LEFT -> movePlacerLeft();
            case RIGHT -> movePlacerRight();
            default -> {}
        }
    }
}
