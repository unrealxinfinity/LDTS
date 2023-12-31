package pt.up.fe.edu.dozer.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;

public class LevelSelectMenuTest {
    LevelSelect behaviourCheck;
    LevelSelect valuesCheck;

    @BeforeEach
    public void setUp() {
        behaviourCheck = Mockito.mock(LevelSelect.class);
        valuesCheck = new LevelSelect();
    }

    @Test
    public void levelSelectMenuTest() {

        Assertions.assertEquals(valuesCheck.getSelectedNum(), 0);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Select Level:");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 1);
        Assertions.assertEquals("1", valuesCheck.getCurrentEntry());
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 2);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Start");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 3);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(), "Back");
    }

    @Test
    public void levelIncrementDigitTest() {

        Assertions.assertEquals(1, valuesCheck.getSelectedLevel());
        valuesCheck.nextEntry();
        Assertions.assertEquals(1, valuesCheck.getSelectedLevel());
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(2, valuesCheck.getSelectedLevel());
    }

    @Test
    public void levelDecrementDigitTest() {

        Assertions.assertEquals(valuesCheck.getSelectedNum(), 0);
        valuesCheck.nextEntry();
        Assertions.assertEquals(1, valuesCheck.getSelectedLevel());
        valuesCheck.incrementCurrentDigit();

        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(1, valuesCheck.getSelectedLevel());
    }

    @Test
    public void isSelectedLevel() {
    }

    @Test
    public void getSelectedLevel() {
        Assertions.assertEquals(1, valuesCheck.getSelectedLevel());
        Assertions.assertEquals(valuesCheck.getSelectedNum(), 0);
        valuesCheck.nextEntry();
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(2, valuesCheck.getSelectedLevel());
        valuesCheck.decrementCurrentDigit();
        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(1, valuesCheck.getSelectedLevel());

    }

}
