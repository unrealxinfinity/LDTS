package pt.up.fe.edu.dozer.model.menu;

public class LevelSelect extends Menu {
    public LevelSelect() {
        super("Select Level:", "1", "Start", "Back");
    }

    public LevelSelect(String... strings) {
        super(strings);
    }

    public void decrementCurrentDigit() {
        if (getSelectedNum() != 1) return;
        int digit = Integer.parseInt(getCurrentEntry());
        setCurrentEntryTo(Integer.toString((digit == 1) ? digit : --digit));
    }

    public void incrementCurrentDigit() {
        if (getSelectedNum() != 1) return;
        int digit = Integer.parseInt(getCurrentEntry());
        digit++;
        setCurrentEntryTo(Integer.toString(digit));
    }

    protected void setCurrentEntryTo(String s) {
        entries.set(getSelectedNum(), s);
    }

    public int getSelectedLevel() {
        return Integer.parseInt(entries.get(1));
    }
}
