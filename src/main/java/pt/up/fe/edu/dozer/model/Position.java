package pt.up.fe.edu.dozer.model;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position moveLeft() {
        return new Position(x - 1, y);
    }

    public Position moveRight() {
        return new Position(x + 1, y);
    }

    public Position moveUp() {
        return new Position(x, y - 1);
    }

    public Position moveDown() {
        return new Position(x, y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
