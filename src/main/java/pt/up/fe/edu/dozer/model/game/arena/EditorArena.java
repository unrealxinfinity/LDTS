package pt.up.fe.edu.dozer.model.game.arena;

import pt.up.fe.edu.dozer.model.game.elements.Placer;

public class EditorArena extends Arena {
    public EditorArena(int width, int height) {
        super(width, height);

        this.placer = new Placer(5, 5);
    }

    public Placer getPlacer() {
        return placer;
    }

    public void setPlacer(Placer placer) {
        this.placer = placer;
    }

    private Placer placer;
}
