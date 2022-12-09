package pt.up.fe.edu.dozer.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.menu.LevelEditorMenu;

import javax.xml.validation.Validator;
import java.util.logging.Level;

public class LevelEditorMenuTest {
    LevelEditorMenu behaviourCheck;
    LevelEditorMenu valuesCheck;

    @BeforeEach
    public void setUp(){
        behaviourCheck= Mockito.mock(LevelEditorMenu.class);
        valuesCheck=new LevelEditorMenu();
    }
    @Test
    public void LevelEditorMenuTest(){

        Assertions.assertEquals(3,valuesCheck.getNumberEntries());

    }
    @Test
    public void incrementCurrentDigitTest(){

        Assertions.assertEquals(1,valuesCheck.getSelectedLevel());
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(1,valuesCheck.getSelectedLevel());
        valuesCheck.nextEntry();
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(2,valuesCheck.getSelectedLevel());

    }

    @Test
    public void decrementCurrentDigitTest(){
        Assertions.assertEquals(1,valuesCheck.getSelectedLevel());
        valuesCheck.nextEntry();
        valuesCheck.incrementCurrentDigit();
        Assertions.assertEquals(2,valuesCheck.getSelectedLevel());
        valuesCheck.nextEntry();
        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(2,valuesCheck.getSelectedLevel());
        valuesCheck.previousEntry();
        valuesCheck.decrementCurrentDigit();
        Assertions.assertEquals(1,valuesCheck.getSelectedLevel());


    }
}
