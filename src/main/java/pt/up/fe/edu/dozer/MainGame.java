package pt.up.fe.edu.dozer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import pt.up.fe.edu.dozer.controller.menuController.MenuController;

import pt.up.fe.edu.dozer.gui.LanternaGUI;

import pt.up.fe.edu.dozer.model.game.arena.LoaderArenaBuilder;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.GameState;
import pt.up.fe.edu.dozer.state.MenuState.MenuState;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


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

    public void start() throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("/font/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        Font loadedFont = font.deriveFont(Font.PLAIN, 10);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(20, 13));
        Terminal terminal = factory.createTerminal();

        Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        screen.refresh();


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
