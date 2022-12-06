package pt.up.fe.edu.dozer.gui;

import pt.up.fe.edu.dozer.model.Position;

import java.io.IOException;

public interface GUI {
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    ACTION getNextAction() throws IOException;

    void drawDozer(Position position);

    void drawWall(Position position);

    void drawTarget(Position position);

    void drawBoulder(Position position);
    void drawText(Position position, String text, String color);


    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, PAUSE, SELECT, RESTART};
}
