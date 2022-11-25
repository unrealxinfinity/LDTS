package pt.up.fe.edu.dozer;

import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.state.MenuState.MenuState;
import pt.up.fe.edu.dozer.state.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainGame {
    private State state;

    public void setState(State s){
        state=s;
    }
    public MainGame() throws FontFormatException, IOException, URISyntaxException {

        this.state = new MenuState(new MainMenu());
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}