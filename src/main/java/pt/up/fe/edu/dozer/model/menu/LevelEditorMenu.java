package pt.up.fe.edu.dozer.model.menu;

public class LevelEditorMenu extends LevelSelect{
    //Used Decorator Pattern because LevelEditorMenu is just an extension of Level Selector with the same functionalities but added methods
    public LevelEditorMenu(){
        super("Save as level:","0","Save");
    }
    @Override
    public void decrementCurrentDigit(){
        if (getSelectedNum() !=1) return;
        int digit = Integer.parseInt(getCurrentEntry());
        String number=Integer.toString(digit==0?digit:--digit);
        setCurrentEntryTo(number);
    }
    @Override
    public void incrementCurrentDigit(){
        if (getSelectedNum() !=1) return;
        int digit = Integer.parseInt(getCurrentEntry());
        String number=Integer.toString(++digit);
        setCurrentEntryTo(number);
    }
}
