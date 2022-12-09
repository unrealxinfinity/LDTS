package pt.up.fe.edu.dozer.state.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.state.editor.DozerEditorState;
import pt.up.fe.edu.dozer.state.editor.EditorState;

public class EditorStatesTest {
    @Test
    public void getViewer() {
        EditorState state = new DozerEditorState(new EditorArena(20, 20));

        Assertions.assertNotNull(state.getViewer());
    }

    @Test
    public void dozerState() {
        DozerEditorState state = new DozerEditorState(new EditorArena(20,20));

        Assertions.assertNotNull(state.getController());
    }

    @Test
    public void boulderState() {
        BoulderEditorState state = new BoulderEditorState(new EditorArena(20,20));

        Assertions.assertNotNull(state.getController());
    }

    @Test
    public void targetState() {
        TargetEditorState state = new TargetEditorState(new EditorArena(20,20));

        Assertions.assertNotNull(state.getController());
    }

    @Test
    public void wallState() {
        WallEditorState state = new WallEditorState(new EditorArena(20,20));

        Assertions.assertNotNull(state.getController());
    }
}
