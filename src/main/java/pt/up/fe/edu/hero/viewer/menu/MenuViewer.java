package pt.up.fe.edu.hero.viewer.menu;

import pt.up.fe.edu.hero.gui.GUI;
import pt.up.fe.edu.hero.model.Position;
import pt.up.fe.edu.hero.model.menu.Menu;
import pt.up.fe.edu.hero.viewer.Viewer;

import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer (Menu menu){super(menu);}
    public void drawElements(GUI gui){
        gui.drawText(new Position(10,10),"Main Menu","#FF0000");
        for (int i=0;i< getModel().getNumberEntries();i++){
            gui.drawText(new Position(10,13+i) , getModel().getEntry(i) , getModel().isSelected(i) ? "#F0F8FF" :"#FFFFFF");
        }
 }
}
