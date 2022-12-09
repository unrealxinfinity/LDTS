package pt.up.fe.edu.dozer.model.menu;

import static java.lang.Math.abs;

public class LevelSelect extends Menu{
    public LevelSelect() {
        super("Select Level:","0","Start", "Back");
    }
    public LevelSelect(String... strings){
        super(strings);
    }

    public void decrementCurrentDigit() {
        if (getSelectedNum() !=1) return;
        int digit = Integer.parseInt(getCurrentEntry());
        if(digit==0)digit=10;
        else digit--;
        setCurrentEntryTo(Integer.toString(digit));
    }

    public void incrementCurrentDigit() {
        if (getSelectedNum() !=1) return;
        int digit = Integer.parseInt(getCurrentEntry());
        digit++;
        digit = digit%11;
        setCurrentEntryTo(Integer.toString(digit));
    }
    protected void setCurrentEntryTo(String s) {
        entries.set(getSelectedNum(), s);
    }
    public int getSelectedLevel(){return Integer.parseInt(entries.get(1));}
}
