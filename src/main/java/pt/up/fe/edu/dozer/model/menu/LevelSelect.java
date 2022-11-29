package pt.up.fe.edu.dozer.model.menu;

public class LevelSelect extends Menu{
    public LevelSelect() {
        super("0","0","Start", "Back");
    }

    @Override
    public void decrementCurrentDigit() {
        if (getSelectedNum() == 2 || getSelectedNum() == 3) return;
        int digit = Integer.parseInt(getCurrentEntry());
        if (digit == 0) digit = 9;
        else digit--;
        setCurrentEntryTo(Integer.toString(digit));
    }
    @Override
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
