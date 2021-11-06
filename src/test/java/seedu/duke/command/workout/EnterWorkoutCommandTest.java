package seedu.duke.command.workout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnterWorkoutCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException, StorageException {
        createOneWorkout();
        storage = new Storage();
    }

    private void createOneWorkout() {
        Workout workout = new Workout("workout");
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validWorkoutIndex_workoutModeChanged() throws GetJackDException {
        EnterWorkoutCommand c = new EnterWorkoutCommand(1);
        c.executeUserCommand(workoutList, storage);
        Assertions.assertEquals(1, Command.workoutMode);
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