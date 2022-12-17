package pt.up.fe.edu.dozer.model.menu;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Level Select", "Level Editor", "Quit");
    }

    public boolean isSelectedSelect() {
        return isSelected(0);
    }

    public boolean isSelectedEditor() {
        return isSelected(1);
    }

    public boolean isSelectedQuit() {
        return isSelected(2);
    }
}
