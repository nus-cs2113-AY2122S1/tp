package seedu.duke.schedule;

import org.junit.jupiter.api.Test;
import seedu.duke.WorkoutTracker;
import seedu.duke.exceptions.workout.WorkoutException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author arvejw
class WorkoutTrackerTest {

    @Test
    void nullArgumentCheck_NullInput_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        assertThrows(WorkoutException.class, () -> wt.nullArgumentCheck(null));
    }

    @Test
    void isWorkoutNumberWithinRange_inputOutOfRangeNegative_failure() {
        WorkoutTracker wt = new WorkoutTracker();
        assertFalse(wt.isWorkoutNumberWithinRange(-1));
    }

    @Test
    void isWorkoutNumberWithinRange_inputOutOfRangePositive_failure() {
        WorkoutTracker wt = new WorkoutTracker();
        assertFalse(wt.isWorkoutNumberWithinRange(999));
    }

    @Test
    void isWorkoutNumberWithinRange_inputWithinRange_success() throws WorkoutException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput, true);
        assertTrue(wt.isWorkoutNumberWithinRange(1));
    }

    @Test
    void isCompletedWorkoutNumberWithinRange_inputWithinRange_success() throws WorkoutException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput, true);
        assertTrue(wt.isWorkoutNumberWithinRange(1));
    }

    @Test
    void deleteWorkout_outOfRange_exceptionThrow() throws WorkoutException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput1 = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput1, true);
        assertThrows(WorkoutException.class, () -> wt.deleteWorkout("-10"));
    }

    @Test
    void deleteWorkout_nonInteger_exceptionThrow() throws WorkoutException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput1 = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput1, true);
        String argumentInput2 = "@!$!@$!";
        assertThrows(NumberFormatException.class, () -> wt.deleteWorkout(argumentInput2));
    }

    @Test
    void deleteWorkout_nullInput_exceptionThrow() throws WorkoutException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput1 = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput1, true);
        assertThrows(WorkoutException.class, () -> wt.deleteWorkout(null));
    }

    @Test
    void addWorkout_invalidDateFormat_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07-07-2021 /t 17:59";
        assertThrows(DateTimeParseException.class, () -> wt.addWorkout(argumentInput, true));
    }

    @Test
    void addWorkout_invalidTimeFormat_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> wt.addWorkout(argumentInput, true));
    }

    @Test
    void addWorkout_nonIntegerCalorie_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c @$! /d 07/07/2021 /t 7:59";
        assertThrows(NumberFormatException.class, () -> wt.addWorkout(argumentInput, true));
    }

    @Test
    void missingDescriptionCheck_missingDescription_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "  /c 123 /d 07/07/2021 /t 07:59";
        assertThrows(WorkoutException.class, () -> wt.missingDescriptionCheck(argumentInput));
    }

    @Test
    void missingDescriptionCheck_validDescription_noExceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test  /c 123 /d 07/07/2021 /t 07:59";
        assertDoesNotThrow(() -> wt.missingDescriptionCheck(argumentInput));
    }
}