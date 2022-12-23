package pt.up.fe.edu.dozer.viewer.editor;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import pt.up.fe.edu.dozer.gui.GUI;
import pt.up.fe.edu.dozer.model.game.arena.EditorArena;
import pt.up.fe.edu.dozer.model.game.elements.Placer;
import pt.up.fe.edu.dozer.viewer.editor.EditorViewer;
import pt.up.fe.edu.dozer.viewer.editor.PlacerViewer;
import pt.up.fe.edu.dozer.viewer.editor.PlacerViewerBuilder;
import pt.up.fe.edu.dozer.viewer.game.GameViewer;

public class EditorViewerTest {

    @Test
    public void drawElementsTest() {
        GameViewer gameViewer = Mockito.mock(GameViewer.class);
        PlacerViewerBuilder builder = Mockito.mock(PlacerViewerBuilder.class);
        PlacerViewer placerViewer = Mockito.mock(PlacerViewer.class);
        EditorArena arena = Mockito.mock(EditorArena.class);
        Placer placer = Mockito.mock(Placer.class);
        GUI gui = Mockito.mock(GUI.class);
        Mockito.when(builder.getPlacerViewer()).thenReturn(placerViewer);
        Mockito.when(arena.getPlacer()).thenReturn(placer);

        EditorViewer editorViewer = new EditorViewer(arena, builder, gameViewer, null);
        InOrder order = Mockito.inOrder(gameViewer, builder, placerViewer);
        editorViewer.drawElements(gui, 0);

        order.verify(gameViewer, Mockito.times(1)).drawElements(Mockito.eq(gui), Mockito.eq((long)0));
        order.verify(builder, Mockito.times(1)).getPlacerViewer();
        order.verify(placerViewer, Mockito.times(1)).draw(Mockito.eq(placer), Mockito.eq(gui));
    }
}
