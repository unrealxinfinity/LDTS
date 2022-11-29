package pt.up.fe.edu.dozer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.up.fe.edu.dozer.controller.gameController.DozerController;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.game.arena.Arena;
import pt.up.fe.edu.dozer.model.game.elements.Dozer;
import pt.up.fe.edu.dozer.model.game.elements.ImportantWall;
import pt.up.fe.edu.dozer.model.game.elements.Wall;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.MenuState;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.game.ElementViewerBuilder;
import pt.up.fe.edu.dozer.viewer.game.GameViewer;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class MainGame {
    private State state;

    public void setState(State s){
        state=s;
    }
    public MainGame() throws FontFormatException, IOException, URISyntaxException {

        this.state = new MenuState(new MainMenu());
    }

    public static void main(String[] args) throws IOException {
        Arena arena = new Arena(20,20);
        arena.setDozer(new Dozer(5,5));
        java.util.List<Wall> walls = Arrays.asList(new ImportantWall(1,1), new ImportantWall(2,2));
        arena.setCollisionWalls(walls);
        DozerController controller = new DozerController(arena);

        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        LanternaGUI gui = new LanternaGUI(screen);

        GameViewer viewer = new GameViewer(arena, new ElementViewerBuilder());

        while (true) {
            viewer.draw(gui);
            controller.step(null, gui.getNextAction(), 0);
        }
    }
}