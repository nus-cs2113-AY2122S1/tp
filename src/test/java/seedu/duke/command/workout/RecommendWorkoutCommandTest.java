package seedu.duke.command.workout;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RecommendWorkoutCommandTest {

    @Test
    void recommend_invalidWorkoutLevel_exceptionThrown() {
        assertThrows(GetJackDException.class, () -> new RecommendWorkoutCommand("sgdtshjnf"));
    }

}