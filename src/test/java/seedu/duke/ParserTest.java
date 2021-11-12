package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.schedule.ScheduleException;
import seedu.duke.exceptions.workout.WorkoutException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    //@@author arvejw
    @Test
    void getCaloriesBurnedForWorkout_negativeCalories_exceptionThrow() {
        String input = "test /c -123";
        assertThrows(WorkoutException.class, () -> Parser.getCaloriesBurnedForWorkout(input));
    }

    //@@author arvejw
    @Test
    void getCaloriesBurnedForWorkout_nonIntegerCalories_exceptionThrow() {
        String input = "test /c !%!@";
        assertThrows(NumberFormatException.class, () -> Parser.getCaloriesBurnedForWorkout(input));
    }

    //@@author arvejw
    @Test
    void getCaloriesBurnedForWorkout_positiveIntegerCalories_noExceptionThrow() {
        String input = "test /c 123";
        assertDoesNotThrow(() -> Parser.getCaloriesBurnedForWorkout(input));
    }

    //@@author arvejw
    @Test
    void getDateNoDateTracker_invalidDateFormat_exceptionThrow() {
        String input = "test /d 1/12/21 /t 17:50";
        assertThrows(DateTimeParseException.class, () -> Parser.getDateNoDateTracker(input));
    }

    //@@author arvejw
    @Test
    void getDateNoDateTracker_validDateFormat_noExceptionThrow() {
        String input = "test /d 12/12/2021 /t 17:50";
        assertDoesNotThrow(() -> Parser.getDateNoDateTracker(input));
    }

    //@@author arvejw
    @Test
    void getScheduleDescription_noDescription_exceptionThrow() {
        String input = "/d 12/12/2021 /t 17:59";
        assertThrows(ScheduleException.class, () -> Parser.getScheduleDescription(input));
    }

    //@@author arvejw
    @Test
    void getScheduleDescription_validDescription_noExceptionThrow() {
        String input = "test /d 12/12/2021 /t 17:59";
        assertDoesNotThrow(() -> Parser.getScheduleDescription(input));
    }

    //@@author arvejw
    @Test
    void getActivities_noQuantifier_exceptionThrow() {
        String input = "add schedule test123 /d 28/12/2021 /t 18:59 /a squats:310";
        assertThrows(ScheduleException.class, () -> Parser.getActivities(input));
    }

    //@@author arvejw
    @Test
    void getActivities_noSeparator_exceptionThrow() {
        String input2 = "add schedule test123 /d 28/12/2021 /t 18:59 /a swimming  800";
        assertThrows(ScheduleException.class, () -> Parser.getActivities(input2));
    }

    //@@author arvejw
    @Test
    void getActivities_noMarker_exceptionThrow() {
        String input = "add schedule test123 /d 28/12/2021 /t 18:59 /a squats:3x10 swimming:600";
        assertThrows(ScheduleException.class, () -> Parser.getActivities(input));
    }

    //@@author arvejw
    @Test
    void getActivities_validActivityFormat_noExceptionThrow() {
        String input1 = "add schedule test123 /d 28/12/2021 /t 18:59 /a swimming:310 /r";
        String input2 = "add schedule test123 /d 28/12/2021 /t 18:59 /a squats:3x10 /r";
        String input3 = "add schedule test123 /d 28/12/2021 /t 18:59 /a squats: 3x10, swimming:1000, running:1000";
        String[] inputs = {input1, input2, input3};
        for (String i : inputs) {
            assertDoesNotThrow(() -> Parser.getActivities(i));
        }
    }

    //@@author arvejw
    @Test
    void getActivities_unnecessaryQuantifier_exceptionThrow() {
        String input = "add schedule test123 /d 28/12/2021 /t 18:59 /a squats: 3x10, swimming:1000x123, running:1000";
        assertThrows(ScheduleException.class, () -> Parser.getActivities(input));
    }
}