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

    public boolean isSelectedNumber() {
        return isSelected(1);
    }

    public boolean isSelectedStart() {
        return isSelected(2);
    }

    public boolean isSelectedBack() {
        return isSelected(3);
    }

    public boolean isSelectedTitle() {
        return isSelected(0);
    }

    @Override
    public void nextEntry() {
        super.nextEntry();
        if (isSelectedTitle()) super.nextEntry();
    }

    @Override
    public void previousEntry() {
        super.previousEntry();
        if (isSelectedTitle()) super.previousEntry();
    }
}
