package pt.up.fe.edu.dozer.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.menu.MainMenu;

public class MenuTest {

    @Test
    public void MainMenuTest(){
        MainMenu menu= Mockito.mock(MainMenu.class);
        MainMenu valuesCheck= new MainMenu();
        menu.nextEntry();
        Mockito.verify(menu, Mockito.times(1)).nextEntry();
        menu.previousEntry();
        Mockito.verify(menu, Mockito.times(1)).previousEntry();
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),1);
        valuesCheck.previousEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),0);
    }
}
