package pt.up.fe.edu.dozer.model;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {this.x = x; this.y = y;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position setLeft(){ return new Position(x-1,y); }
    public Position setRight(){return new Position(x+1,y); }
    public Position setUp(){return new Position(x,y-1);}
    public Position setDown(){ return new Position(x,y+1);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
}
