package pt.up.fe.edu.dozer.viewer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.edu.dozer.viewer.editor.PlacerViewerBuilder;

public class PlacerViewerBuilderTest {

    @Test
    public void checkNotNull() {
        PlacerViewerBuilder builder = new PlacerViewerBuilder();

        Assertions.assertNotNull(builder.getPlacerViewer());
    }
}
