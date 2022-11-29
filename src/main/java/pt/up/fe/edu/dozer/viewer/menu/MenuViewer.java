package pt.up.fe.edu.dozer.viewer.menu;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer (Menu menu){super(menu);}
    public void drawElements(GUI gui){

        for (int i=0;i< getModel().getNumberEntries();i++){
            gui.drawText(new Position(10,10+i) , getModel().getEntry(i) , (getModel().isSelected(i)) ? "#FF0000" :"#FFFFFF");
        }
        System.out.println(getModel().getCurrentEntry());
    }
}
