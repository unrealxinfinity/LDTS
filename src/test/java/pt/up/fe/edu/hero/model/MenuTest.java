package pt.up.fe.edu.hero.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.hero.model.menu.LevelSelect;
import pt.up.fe.edu.hero.model.menu.MainMenu;

public class MenuTest {
    @Test
    public void getCurrentEntryTest() {
        MainMenu menu = new MainMenu();
        String entry;

        entry = menu.getCurrentEntry();

        Assertions.assertEquals("Level Select", entry);
    }

    @Test
    public void loopAroundIncrementTest() {
        MainMenu menu = new MainMenu();
        String entry;

        menu.nextEntry();
        menu.nextEntry();
        menu.nextEntry();

        entry = menu.getCurrentEntry();

        Assertions.assertEquals("Level Select", entry);
    }

    @Test
    public void loopAroundDecrementTest() {
        MainMenu menu = new MainMenu();
        String entry;

        menu.previousEntry();

        entry = menu.getCurrentEntry();

        Assertions.assertEquals("Quit", entry);
    }

    @Test
    public void getNumberEntriesTest() {
        MainMenu menu = new MainMenu();

        int number = menu.getNumberEntries();

        Assertions.assertEquals(3, number);
    }

    @Test
    public void isSelectedTest() {
        MainMenu menu = new MainMenu();

        menu.nextEntry();
        menu.nextEntry();

        Assertions.assertTrue(menu.isSelected(2));
    }

    @Test
    public void getEntryTest() {
        MainMenu menu = new MainMenu();

        String entry = menu.getEntry(1);

        Assertions.assertEquals("Level Editor", entry);
    }

    @Test
    public void decrementDigitTest() {
        LevelSelect menu = new LevelSelect();

        menu.decrementCurrentDigit();

        Assertions.assertEquals("9", menu.getEntry(0));
    }

    @Test
    public void incrementDigitTest() {
        LevelSelect menu = new LevelSelect();

        menu.incrementCurrentDigit();

        Assertions.assertEquals("1", menu.getEntry(0));
    }

    @Test
    public void incrementDigitLoopBack() {
        LevelSelect menu = new LevelSelect();

        menu.nextEntry();
        for (int i = 0; i < 10; i++) menu.incrementCurrentDigit();

        Assertions.assertEquals("0", menu.getEntry(1));
    }

    @Test
    public void tryIncrementNotDigit() {
        LevelSelect menu = new LevelSelect();

        menu.previousEntry();
        menu.incrementCurrentDigit();

        Assertions.assertEquals("0", menu.getEntry(0));
        Assertions.assertEquals("0", menu.getEntry(1));
        Assertions.assertEquals("Back", menu.getEntry(3));
    }

    @Test
    public void tryDecrementNotDigit() {
        LevelSelect menu = new LevelSelect();

        menu.nextEntry();
        menu.nextEntry();

        menu.decrementCurrentDigit();

        Assertions.assertEquals("0", menu.getEntry(0));
        Assertions.assertEquals("0", menu.getEntry(1));
        Assertions.assertEquals("Start", menu.getEntry(2));
    }
}
