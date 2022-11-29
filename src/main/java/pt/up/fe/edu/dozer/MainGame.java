package pt.up.fe.edu.dozer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;
import pt.up.fe.edu.dozer.controller.menuController.MenuController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
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
    public void start() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        this.gui=new LanternaGUI(screen);

        MainMenu mainMenu=new MainMenu();
        menuController=new MenuController(mainMenu);
        menuViewer=new MenuViewer(mainMenu);

        this.gui = new LanternaGUI(screen);

        while (this.state != null) {
            state.step(this,gui,0);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new MainGame().start();
    }
}
