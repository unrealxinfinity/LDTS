package pt.up.fe.edu.dozer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import pt.up.fe.edu.dozer.controller.menuController.MenuController;

import pt.up.fe.edu.dozer.gui.LanternaGUI;

import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.MenuState.MenuState;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class MainGame {
    private State state;

    private LanternaGUI gui;
    private MenuController menuController;
    private MenuViewer menuViewer;

    public void setState(State s){
        state=s;
    }
    public MainGame() throws FontFormatException, IOException, URISyntaxException {
        this.state = new MenuState(new MainMenu());
    }


    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new MainGame().start();
    }

    public void start() throws IOException {

        TerminalSize terminalSize = new TerminalSize(20, 13);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();


        LanternaGUI gui = new LanternaGUI(screen);

        int frameTime = 50;
        while (true) {
            long startTime = System.currentTimeMillis();
            state.step(this, gui, 0);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e ) {}
        }
    }
}
