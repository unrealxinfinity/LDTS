package pt.up.fe.edu.hero.model.menu;

public class LevelSelect extends Menu{
    public LevelSelect() {
        super("0","0","Start", "Back");
    }

    public boolean isSelectedFirstDigit() {
        return isSelected(0);
    }

    public boolean isSelectedSecondDigit() {
        return isSelected(1);
    }

    public boolean isSelectedStart() {
        return isSelected(2);
    }

    public boolean isSelectedBack() {
        return isSelected(3);
    }

    public void decrementCurrentDigit() {
        if (getSelectedNum() == 2 || getSelectedNum() == 3) return;
        int digit = Integer.parseInt(getCurrentEntry());
        if (digit == 0) digit = 9;
        else digit--;
        setCurrentEntryTo(Integer.toString(digit));
    }

    public void incrementCurrentDigit() {
        if (getSelectedNum() == 2 || getSelectedNum() == 3) return;
        int digit = Integer.parseInt(getCurrentEntry());
        if (digit == 9) digit = 0;
        else digit++;
        setCurrentEntryTo(Integer.toString(digit));
    }

    protected void setCurrentEntryTo(String s) {
        entries.set(getSelectedNum(), s);
    }
}
