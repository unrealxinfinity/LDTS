package pt.up.fe.edu.dozer.controller.MenuController;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.MainGame;
import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.controller.menuController.MenuController;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MenuControllerTest {
    MenuController behaviourCheck;
    MenuController InternalsCheck;
    MenuController valuesCheck;
    MainGame game;
    LanternaGUI gui;
    Screen screen;
    MainMenu menuMock;
    TextGraphics graphics;
    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        behaviourCheck= Mockito.mock(MenuController.class);
        menuMock=Mockito.mock(MainMenu.class);
        InternalsCheck=new MenuController(menuMock); // Passar para construtor para testar se o mock for chamado dentro dos metodos

        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        game=Mockito.mock(MainGame.class);
        gui=Mockito.mock(LanternaGUI.class);


    }
    @Test
    public void MenuControllerTest(){
        //Assertions.assertEquals(valuesCheck.getModel().getNumberEntries(),3);
    }
    @Test
    public void stepTest() throws IOException {
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN);

        InternalsCheck.step(game,gui.getNextAction(),0);
        Mockito.verify(menuMock,Mockito.times(1)).nextEntry();
        Mockito.verify(gui,Mockito.times(1)).getNextAction();
        behaviourCheck.step(game,gui.getNextAction(),0);
        Mockito.verify(behaviourCheck,Mockito.times(1)).step(game,gui.getNextAction(),0);



        //Gostava de testar se o getModel esta a ser chamado com o step neste mock aqui mas alguma coisa n esta certo
        // pois o getModel nao esta a ser chamado






    }
}
