package pt.up.fe.edu.dozer.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;

public class MenuTest {
    Menu behaviourCheck;
    Menu valuesCheck;

    @BeforeEach
    public void setUp() {
        behaviourCheck = Mockito.mock(MainMenu.class);
        valuesCheck = new MainMenu();
    }

    @Test
    public void MainMenuTest() {

        Assertions.assertEquals(valuesCheck.getSelectedNum(), 0);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Level Select");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 1);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Level Editor");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 2);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Quit");
    }

    @Test
    public void isSelectedTest() {
        Assertions.assertEquals(valuesCheck.isSelected(0), true);
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.isSelected(0), false);
        Assertions.assertEquals(valuesCheck.isSelected(-1), false);
        Assertions.assertEquals(valuesCheck.isSelected(3), false);
        Assertions.assertEquals(valuesCheck.isSelected(1), true);
    }

    @Test
    public void getCurrentEntryTest() {
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Level Select");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Level Editor");
    }

    @Test
    public void getEntryTest() {
        Assertions.assertEquals(valuesCheck.getEntry(2), "Quit");
    }

    @Test
    public void getNumberEntriesTest() {
        Assertions.assertEquals(valuesCheck.getNumberEntries(), 3);
    }

    @Test
    public void getSelectedNumTest() {
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 0);
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 1);
    }

    @Test
    public void nextEntryTest() {
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 1);
        valuesCheck.nextEntry();
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 0);
    }

    @Test
    public void previousEntryTest() {
        valuesCheck.previousEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 2);
        valuesCheck.previousEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 1);
    }
}
