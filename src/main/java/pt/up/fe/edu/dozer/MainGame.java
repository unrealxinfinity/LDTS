package pt.up.fe.edu.dozer;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import pt.up.fe.edu.dozer.controller.menu.MenuController;

import pt.up.fe.edu.dozer.gui.LanternaGUI;

import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.editor.*;
import pt.up.fe.edu.dozer.state.menu.MenuState;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class MainGame {
    private State state;
    private long initialTime = System.currentTimeMillis();


    public void setState(State s){
        state=s;
    }

    public void resetTimer() {
        initialTime = System.currentTimeMillis();
    }
    public MainGame() throws FontFormatException, IOException, URISyntaxException {
        this.state = new MenuState(new MainMenu());
    }


    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new MainGame().start();
    }


    public void start() throws IOException, FontFormatException, URISyntaxException {
        URL resource = MainGame.class.getResource("/font/JoystixMonospace-Regular.ttf");
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        Font loadedFont = font.deriveFont(Font.PLAIN, 20);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(20, 15));
        Terminal terminal = factory.createTerminal();

        Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        screen.refresh();

        LanternaGUI gui = new LanternaGUI(screen);
        int frameTime = 50;
        while (this.state!=null) {
            long startTime = System.currentTimeMillis();
            long elapsedTime = System.currentTimeMillis() - startTime;
            state.step(this, gui, (startTime-initialTime)/1000);
            long sleepTime = frameTime - elapsedTime;
            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e ) {}
        }
        screen.close();
    }
}
