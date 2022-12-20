package pt.up.fe.edu.dozer.viewer.menu;

import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.Position;
import pt.up.fe.edu.dozer.model.menu.Menu;
import pt.up.fe.edu.dozer.viewer.Viewer;

public class MenuViewer<T extends Menu> extends Viewer<T> {
    public MenuViewer(T menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui, long time) {
        gui.drawText(new Position(6, 1), "BullDozer", "#FFFF00");
        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            boolean selected = getModel().isSelected(i);
            String entry = getModel().getEntry(i);
            gui.drawText(new Position(4, 5 + i), entry, selected ? "#FF0000" : "#FFFFFF");
        }
    }
}
