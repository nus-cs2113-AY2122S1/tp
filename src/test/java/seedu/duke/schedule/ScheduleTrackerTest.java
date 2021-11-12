package seedu.duke.schedule;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.schedule.ScheduleException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author arvejw
class ScheduleTrackerTest {

    @Test
    void nullArgumentCheck_NullInput_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        assertThrows(ScheduleException.class, () -> st.nullArgumentCheck(null));
    }

    @Test
    void isScheduledWorkoutNumberWithinRange_inputOutOfRangeNegative_failure() {
        ScheduleTracker st = new ScheduleTracker();
        assertFalse(st.isScheduledWorkoutNumberWithinRange(-256));
    }

    @Test
    void isScheduledWorkoutNumberWithinRange_inputOutOfRangePositive_failure() {
        ScheduleTracker st = new ScheduleTracker();
        assertFalse(st.isScheduledWorkoutNumberWithinRange(999));
    }

    @Test
    void isScheduledWorkoutNumberWithinRange_inputWithinRange_success() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, false);
        assertTrue(st.isScheduledWorkoutNumberWithinRange(1));
    }

    @Test
    void scheduledWorkoutSeparatorCheck_MissingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 17:59";
        assertThrows(ScheduleException.class, () -> st.scheduledWorkoutSeparatorCheck(argumentInput));
    }

    @Test
    void deleteScheduledWorkout_outOfRangeNegative_exceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, false);
        assertThrows(ScheduleException.class, () -> st.deleteScheduledWorkout("-1"));
    }

    @Test
    void deleteScheduledWorkout_outOfRangePositive_exceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, false);
        assertThrows(ScheduleException.class, () -> st.deleteScheduledWorkout("999"));
    }

    @Test
    void deleteScheduledWorkout_validIndex_noExceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, false);
        assertDoesNotThrow(() -> st.deleteScheduledWorkout("1"));
    }

    @Test
    void deleteScheduledWorkout_nonInteger_exceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, false);
        String argumentInput2 = "@!$!@$!";
        assertThrows(NumberFormatException.class, () -> st.deleteScheduledWorkout(argumentInput2));
    }

    @Test
    void deleteScheduledWorkout_nullInput_exceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, false);
        assertThrows(ScheduleException.class, () -> st.deleteScheduledWorkout(null));
    }

    @Test
    void addScheduledWorkout_missingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 17:59";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(argumentInput, false, false));
    }

    @Test
    void addScheduledWorkout_invalidDateFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07-07-2022 /t 17:59";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput, false, false));
    }

    @Test
    void addScheduledWorkout_invalidTimeFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput, false, false));
    }

    @Test
    void missingDescriptionCheck_missingDescription_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = " /d 07/07/2022 /t 07:59";
        assertThrows(ScheduleException.class, () -> st.missingDescriptionCheck(argumentInput));
    }

    @Test
    void missingDescriptionCheck_validDescription_noExceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 /t 07:59";
        assertDoesNotThrow(() -> st.missingDescriptionCheck(argumentInput));
    }

    @Test
    void addScheduledWorkout_noActivityQuantifier_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats:310";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, false));
    }

    @Test
    void addScheduledWorkout_noActivitySeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a swimming  800";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, false));
    }

    @Test
    void addScheduledWorkout_noActivityMarker_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats:3x10 swimming:600";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, false));
    }

    @Test
    void addScheduledWorkout_validActivityFormat_noExceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input1 = "test123 /d 28/12/2021 /t 18:59 /a swimming:310 /r";
        String input2 = "test123 /d 28/12/2021 /t 18:59 /a squats:3x10 /r";
        String input3 = "test123 /d 28/12/2021 /t 18:59 /a squats: 3x10, swimming:1000, running:1000";
        String[] inputs = {input1, input2, input3};
        for (String i : inputs) {
            assertDoesNotThrow(() -> st.addScheduledWorkout(i, false, false));
        }
    }

    @Test
    void addScheduledWorkout_invalidDistanceActivityFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats: 3x10, swimming:%!@, running:1000";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, false));
    }

    @Test
    void addScheduledWorkout_invalidNonDistanceActivityFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats: @!$#!x10, swimming:1000, running:1000";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, false));
    }

    @Test
    void addScheduledWorkout_unnecessaryActivityQuantifier_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats: 3x10, swimming:1000x123, running:1000";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, false));
    }
}
