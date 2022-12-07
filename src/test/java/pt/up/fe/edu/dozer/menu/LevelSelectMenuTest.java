package pt.up.fe.edu.dozer.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.Menu;

public class LevelSelectMenuTest {
    LevelSelect behaviourCheck;
    LevelSelect valuesCheck;
    @BeforeEach
    public void setUp(){
        behaviourCheck = Mockito.mock(LevelSelect.class);
        valuesCheck = new LevelSelect();
    }
    @Test
    public void LevelSelectMenuTest(){

        Assertions.assertEquals(valuesCheck.getSelectedNum(),0);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Select Level:");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),1);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"0");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),2);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Start");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),3);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Back");
    }
    @Test
    public void LevelIncrementDigitTest(){

        behaviourCheck.incrementCurrentDigit();
        Mockito.verify(behaviourCheck,Mockito.times(1)).incrementCurrentDigit();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),0);
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),0);
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),1);
    }
    @Test
    public void LevelDecrementDigitTest(){

        Assertions.assertEquals(valuesCheck.getSelectedNum(),0);
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),0);
        valuesCheck.incrementCurrentDigit();

        behaviourCheck.decrementCurrentDigit();
        Mockito.verify(behaviourCheck,Mockito.times(1)).decrementCurrentDigit();

        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),0);
    }
    @Test
    public void isSelectedLevel(){
        behaviourCheck.getSelectedLevel();
        Mockito.verify(behaviourCheck,Mockito.times(1)).getSelectedLevel();
    }
    @Test
    public void  getSelectedLevel(){
        behaviourCheck.getSelectedLevel();
        Mockito.verify(behaviourCheck,Mockito.times(1)).getSelectedLevel();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),0);
        Assertions.assertEquals(valuesCheck.getSelectedNum(),0);
        valuesCheck.nextEntry();
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),1);
        valuesCheck.decrementCurrentDigit();
        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(valuesCheck.getSelectedLevel(),10);

    }

}
