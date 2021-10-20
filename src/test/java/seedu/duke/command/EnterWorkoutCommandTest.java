package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.workout.EnterWorkoutCommand;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnterWorkoutCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException {
        createOneWorkout();
        storage = new Storage();
    }

    private void createOneWorkout() {
        Workout workout = new Workout("workout", LocalDate.parse("2021-12-25"));
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validWorkoutIndex_workoutModeChanged() throws GetJackDException {
        EnterWorkoutCommand c = new EnterWorkoutCommand(1);
        c.executeUserCommand(workoutList, storage);
        assertEquals(1, Command.workoutMode);
    }

    @Test
    void executeUserCommand_invalidWorkoutIndexGreaterThanWorkoutsSize_exceptionThrown() {
        EnterWorkoutCommand c = new EnterWorkoutCommand(3);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }

    @Test
    void executeUserCommand_zeroOrNegativeIndex_exceptionThrown() {
        EnterWorkoutCommand c1 = new EnterWorkoutCommand(0);
        EnterWorkoutCommand c2 = new EnterWorkoutCommand(-4);
        assertThrows(GetJackDException.class, () -> c1.executeUserCommand(workoutList, storage));
        assertThrows(GetJackDException.class, () -> c2.executeUserCommand(workoutList, storage));
    }
}