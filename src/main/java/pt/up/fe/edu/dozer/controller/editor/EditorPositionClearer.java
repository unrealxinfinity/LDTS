package pt.up.fe.edu.dozer.controller.editor;

import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Wall;

public class EditorPositionClearer {
    private final EditorArena arena;

    public EditorPositionClearer(EditorArena arena) {
        this.arena = arena;
    }

    public void clearPosition(Position position) {
        if (arena.getDozer() != null && arena.getDozer().getPosition().equals(position)) arena.setDozer(null);
        arena.getCollisionWalls().removeIf(w -> w.getPosition().equals(position));
        arena.getBoulders().removeIf(b -> b.getPosition().equals(position));
        arena.getTargets().removeIf(t -> t.getPosition().equals(position));
    }
}
