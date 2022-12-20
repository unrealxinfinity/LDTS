package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Placer;

import java.io.IOException;

public class PlacerController extends EditorController {

    public PlacerController(EditorArena arena) {
        super(arena);
    }

    private void movePlacer(Position position) {
        boolean vertical = position.getY() >= 0 && position.getY() < getModel().getHeight();
        boolean horizontal = position.getX() >= 0 && position.getX() < getModel().getWidth();
        if (vertical && horizontal) {
            Placer placer = getModel().getPlacer();
            placer.setPosition(position);
        }
    }

    public void movePlacerLeft() {
        Position position = getModel().getPlacer().getPosition().moveLeft();
        movePlacer(position);
    }

    public void movePlacerRight() {
        Position position = getModel().getPlacer().getPosition().moveRight();
        movePlacer(position);
    }

    public void movePlacerUp() {
        Position position = getModel().getPlacer().getPosition().moveUp();
        movePlacer(position);
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
            default -> {
            }
        }
    }
}
