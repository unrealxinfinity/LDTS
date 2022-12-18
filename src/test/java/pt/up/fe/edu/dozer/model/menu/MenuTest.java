package pt.up.fe.edu.dozer.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuTest {
    Menu valueCheck;
    Menu behaviousCheck;

    @BeforeEach
    public void SetUp(){
        valueCheck=new MainMenu();
    }
    @Test
    public void MenuTest(){

        Assertions.assertEquals(3,valueCheck.getNumberEntries());
    }
    @Test
    public void nextPrevEntryTest(){

        valueCheck.nextEntry();
        Assertions.assertEquals(1,valueCheck.getSelectedNum());
        valueCheck.previousEntry();
        Assertions.assertEquals(0,valueCheck.getSelectedNum());
        valueCheck.previousEntry();
        Assertions.assertEquals(2,valueCheck.getSelectedNum());
        valueCheck.nextEntry();
        Assertions.assertEquals(0,valueCheck.getSelectedNum());
    }
    @Test
    public void getEntryTest(){
        Assertions.assertEquals("Level Select",valueCheck.getEntry(0));
    }

    @Test
    public void getCurrentEntryTest() {
        Assertions.assertEquals("Level Select", valueCheck.getCurrentEntry());
    }
    @Test
    public void getNumberEntriesTest() {
        Assertions.assertEquals(3, valueCheck.getNumberEntries());
    }
    @Test
    public void isSelectedTest() {
        valueCheck.nextEntry();
        valueCheck.nextEntry();
        Assertions.assertTrue(valueCheck.isSelected(2));
        Assertions.assertFalse(valueCheck.isSelected(1000));
    }




    /*@Test
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

        Assertions.assertEquals("Select Level:", menu.getEntry(0));
        Assertions.assertEquals("0", menu.getEntry(1));
        Assertions.assertEquals("Start", menu.getEntry(2));
    }*/
}
