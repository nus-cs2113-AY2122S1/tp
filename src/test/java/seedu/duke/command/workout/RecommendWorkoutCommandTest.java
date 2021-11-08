package seedu.duke.command.workout;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RecommendWorkoutCommandTest {

    @Test
    void recommend_invalidWorkoutLevel_exceptionThrown() {
        assertThrows(GetJackDException.class, () -> new RecommendWorkoutCommand("sgdtshjnf"));
        assertThrows(GetJackDException.class, () -> new RecommendWorkoutCommand("pro pro"));
        assertThrows(GetJackDException.class, () -> new RecommendWorkoutCommand("beginnerbeginner"));
        assertThrows(GetJackDException.class, () -> new RecommendWorkoutCommand("intermediateblahblah"));
    }
    
}