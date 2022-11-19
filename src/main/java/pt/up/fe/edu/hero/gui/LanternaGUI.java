package pt.up.fe.edu.hero.gui;

import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class LanternaGUI implements GUI{
    private final Screen screen;

    public LanternaGUI(Screen screen) {this.screen = screen;}

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
