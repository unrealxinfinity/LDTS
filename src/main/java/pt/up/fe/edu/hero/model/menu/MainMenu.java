package pt.up.fe.edu.hero.model.menu;

public class MainMenu extends Menu{
    public MainMenu() {
        super("Level Select", "Level Editor", "Quit");
    }

    public boolean isSelectedLevelSelect() {
        return isSelected(0);
    }

    public boolean isSelectedLevelEditor() {
        return isSelected(1);
    }

    public boolean isSelectedQuit() {
        return isSelected(2);
    }
}
