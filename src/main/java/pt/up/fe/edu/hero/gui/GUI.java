package pt.up.fe.edu.hero.gui;

import pt.up.fe.edu.hero.model.Position;

import java.io.IOException;

public interface GUI {
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    ACTION getNextAction() throws IOException;

    void drawDozer(Position position);

    void drawWall(Position position);

    void drawTarget(Position position);

    void drawBoulder(Position position, Boolean isInTarget);

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, PAUSE};
}
