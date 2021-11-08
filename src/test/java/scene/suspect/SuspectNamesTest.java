package scene.suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class SuspectNamesTest {

    @Test
    public void suspectNamesEnumTest() {
        SuspectNames father = SuspectNames.SUSPECT_FATHER;
        SuspectNames wendy = SuspectNames.SUSPECT_WENDY;

        String fatherString = "SUSPECT_FATHER";
        assertEquals(fatherString, father.toString());

        assertNotEquals(wendy, father);
    }
}
