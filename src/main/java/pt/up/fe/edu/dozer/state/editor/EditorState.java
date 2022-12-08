package pt.up.fe.edu.dozer.state.editor;

import pt.up.fe.edu.dozer.controller.Controller;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.State;
import pt.up.fe.edu.dozer.viewer.Viewer;
import pt.up.fe.edu.dozer.viewer.game.EditorViewer;

public abstract class EditorState extends State<EditorArena> {
    //NEW
    private String placingElem;
    //added another parameter which each of the subclasses will do super(arena,string);
    public EditorState(EditorArena arena,String elem) {
        super(arena);
        //NEW to pass the placingelem to the editor viewer
        this.placingElem=elem;
        System.out.print(placingElem);
    }
    //this was an attempt
    private String getPlacingElem(){
        return placingElem;
    }

    @Override
    protected Viewer<EditorArena> getViewer() {
        System.out.print(getPlacingElem());

        return new EditorViewer(getModel(),placingElem);
    }

    @Override
    protected abstract Controller<EditorArena> getController();
}
