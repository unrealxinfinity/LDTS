package pt.up.fe.edu.dozer.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.model.menu.LevelSelect;
import pt.up.fe.edu.dozer.model.menu.MainMenu;
import pt.up.fe.edu.dozer.model.menu.Menu;

public class MenuTest {
    Menu behaviourCheck;
    Menu valuesCheck;
    @BeforeEach
    public void setUp(){
        behaviourCheck = Mockito.mock(MainMenu.class);
        valuesCheck = new MainMenu();
    }
    @Test
    public void MainMenuTest(){

        Assertions.assertEquals(valuesCheck.getSelectedNum(),0);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Level Select");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),1);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Level Editor");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),2);
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Quit");
    }
    @Test
    public void isSelectedTest(){
        behaviourCheck.isSelected(0);
        Mockito.verify(behaviourCheck,Mockito.times(1)).isSelected(0);
        Assertions.assertEquals(valuesCheck.isSelected(0),true);
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.isSelected(0),false);
        Assertions.assertEquals(valuesCheck.isSelected(-1),false);
        Assertions.assertEquals(valuesCheck.isSelected(3),false);
        Assertions.assertEquals(valuesCheck.isSelected(1),true);
    }
    @Test
    public void getCurrentEntryTest(){
        behaviourCheck.getCurrentEntry();
        Mockito.verify(behaviourCheck,Mockito.times(1)).getCurrentEntry();
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Level Select");
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getCurrentEntry(),"Level Editor");
    }
    @Test
    public void getEntryTest(){
        behaviourCheck.getEntry(0);
        Mockito.verify(behaviourCheck,Mockito.times(1)).getEntry(0);
        Assertions.assertEquals(valuesCheck.getEntry(2),"Quit");
    }
    @Test
    public void getNumberEntriesTest(){
        behaviourCheck.getNumberEntries();
        Mockito.verify(behaviourCheck,Mockito.times(1)).getNumberEntries();
        Assertions.assertEquals(valuesCheck.getNumberEntries(),3);
    }
    @Test
    public void getSelectedNumTest(){
        behaviourCheck.getSelectedNum();
        Mockito.verify(behaviourCheck,Mockito.times(1)).getSelectedNum();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),0);
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),1);
    }
    @Test
    public void nextEntryTest(){
        behaviourCheck.nextEntry();
        Mockito.verify(behaviourCheck, Mockito.times(1)).nextEntry();
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),1);
        valuesCheck.nextEntry();
        valuesCheck.nextEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),0);
    }
    @Test
    public void previousEntryTest(){
        behaviourCheck.previousEntry();
        Mockito.verify(behaviourCheck, Mockito.times(1)).previousEntry();
        valuesCheck.previousEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),2);
        valuesCheck.previousEntry();
        Assertions.assertEquals(valuesCheck.getSelectedNum(),1);
    }
}
