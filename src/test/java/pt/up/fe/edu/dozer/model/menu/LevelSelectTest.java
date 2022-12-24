package pt.up.fe.edu.dozer.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LevelSelectTest {
    LevelSelect valuesCheck;

    @BeforeEach
    public void setUp(){
        valuesCheck=new LevelSelect();

    }
    @Test
    public void LevelSelectTest(){
        Assertions.assertEquals(4,valuesCheck.getNumberEntries());
    }
    @Test
    public void incrementDigitTest() {
        Assertions.assertEquals(0,valuesCheck.getSelectedNum());
        valuesCheck.nextEntry();
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals("2", valuesCheck.getEntry(1));
    }
    @Test
    public void decrementDigitTest() {
        Assertions.assertEquals(0,valuesCheck.getSelectedNum());
        valuesCheck.nextEntry();
        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(1, valuesCheck.getSelectedLevel());
        valuesCheck.incrementCurrentDigit();
        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(1,valuesCheck.getSelectedLevel());
    }
    @Test
    public void setCurrentEntryToTest(){
        Assertions.assertEquals(0,valuesCheck.getSelectedNum());
        valuesCheck.setCurrentEntryTo("Ola");
        Assertions.assertEquals("Ola",valuesCheck.getEntry(0));
        valuesCheck.nextEntry();
        Assertions.assertEquals(1,valuesCheck.getSelectedNum());
        valuesCheck.setCurrentEntryTo("Adeus");
        Assertions.assertEquals("Adeus",valuesCheck.getCurrentEntry());
    }
    @Test
    public void getSelectedLevelTest(){
        Assertions.assertEquals(1,valuesCheck.getSelectedLevel());
        valuesCheck.nextEntry();
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(2,valuesCheck.getSelectedLevel());
    }
    @Test
    public void isSelectedFunctionsTest(){
        Assertions.assertTrue(valuesCheck.isSelectedTitle());
        valuesCheck.nextEntry();
        Assertions.assertTrue(valuesCheck.isSelectedNumber());
        valuesCheck.nextEntry();
        Assertions.assertTrue(valuesCheck.isSelectedStart());
        valuesCheck.nextEntry();
        Assertions.assertTrue(valuesCheck.isSelectedBack());
    }
    @Test
    public void tryDecrementNotDigit() {
        LevelSelect menu = new LevelSelect();

        menu.nextEntry();

        menu.decrementCurrentDigit();

        Assertions.assertEquals("Select Level:", menu.getEntry(0));
        Assertions.assertEquals("1", menu.getEntry(1));
        Assertions.assertEquals("Start", menu.getEntry(2));
    }
}
