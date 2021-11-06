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

class ExitWorkoutCommandTest {
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
    void executeUserCommand_enterThenExitWorkoutOne_workoutModeChanged() throws GetJackDException {
        EnterWorkoutCommand c = new EnterWorkoutCommand(1);
        c.executeUserCommand(workoutList, storage);
        Assertions.assertEquals(1, Command.workoutMode);
        ExitWorkoutCommand c2 = new ExitWorkoutCommand();
        c2.executeUserCommand(workoutList, storage);
        assertEquals(0, Command.workoutMode);
    }
}