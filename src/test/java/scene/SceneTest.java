package scene;

import scene.narrative.Narrative;
import org.junit.jupiter.api.Test;
import scene.suspect.SuspectList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SceneTest {

    /*
    @Test
    public void getNarrative() {
        Scene scene = new Scene(new Narrative(), new SuspectList(), SceneTypes.INTRODUCTION_SCENE);
        assertThrows(FileNotFoundException.class, scene::runScene);
    }*/

    @Test
    public void toString_InstantiateScene_printNoNarrativeMessage() {
        String expectedResult = "Incomplete Scene";
        Scene scene = new Scene(new Narrative(), new SuspectList(), SceneTypes.INTRODUCTION_SCENE);
        String result = scene.toString();
        assertEquals(expectedResult, result);
    }
}
