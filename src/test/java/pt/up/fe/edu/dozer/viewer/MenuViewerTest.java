package pt.up.fe.edu.dozer.viewer;


import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.gui.LanternaGUI;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.viewer.menu.MenuViewer;

public class MenuViewerTest {
    private LanternaGUI gui;
    private Screen screen;
    private TextGraphics graphics;
    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);

        gui = new LanternaGUI(screen);
    }

    @Test
    public void drawElementTest() {
        Menu main = new MainMenu();
        MenuViewer<Menu> menu= new MenuViewer<>(main);
        menu.drawElements(gui,0);
        InOrder order = Mockito.inOrder(graphics);
        
        order.verify(graphics, Mockito.times(1)).setForegroundColor(Mockito.eq(TextColor.Factory.fromString("#FF0000")));
        order.verify(graphics, Mockito.times(1)).putString(4,5 ,"Level Select");
        order.verify(graphics, Mockito.times(1)).setForegroundColor(Mockito.eq(TextColor.Factory.fromString("#FFFFFF")));
        order.verify(graphics, Mockito.times(1)).putString(4,6 ,"Level Editor");
        order.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        order.verify(graphics, Mockito.times(1)).putString(4,7 ,"Quit");
    }
}
