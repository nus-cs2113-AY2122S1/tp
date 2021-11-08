package seedu.duke.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.data.Exercise;

public class ExerciseTest {

    @Test
    //Also tests exercise.toString()
    public void exercise_setDone_toStringReflectsIsDoneStatus() {
        Exercise exercise = new Exercise("Push-ups", 5, 20);
        exercise.setDone();
        assertEquals("[X] Push-ups | 5 sets of 20 reps", exercise.toString());
    }
}
