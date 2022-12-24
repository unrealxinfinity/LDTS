package pt.up.fe.edu.dozer.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        String entry = valueCheck.getEntry(1);
        Assertions.assertEquals("Level Editor", entry);
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













}
