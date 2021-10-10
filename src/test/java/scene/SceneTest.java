package scene;

import narrative.Narrative;
import org.junit.jupiter.api.Test;
import search.Search;
import seedu.duke.Ui;
import suspect.SuspectList;


import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SceneTest {

    @Test
    public void getNarrative() {
        Search search = new Search(new SuspectList(new Ui()));
        Scene scene = new Scene(new Narrative(), search);
        assertThrows(FileNotFoundException.class, () -> scene.runScene());
    }

    @Test
    public void toString_InstantiateScene_printNoNarrativeMessage() {
        String expectedResult = "Incomplete Scene";
        Search search = new Search(new SuspectList(new Ui()));
        Scene scene = new Scene(new Narrative(), search);
        String result = scene.toString();
        assertEquals(expectedResult, result);
    }
}
