package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnterWorkoutCommandTest {
    private WorkoutList workoutList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    public void setUp() throws GetJackDException {
        createOneWorkout();
        storage = new Storage();
        ui = new Ui();
    }

    private void createOneWorkout() {
        Workout workout = new Workout("workout");
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validWorkoutIndex_workoutModeChanged() throws GetJackDException {
        EnterWorkoutCommand c = new EnterWorkoutCommand(1);
        c.executeUserCommand(workoutList, ui, storage);
        assertEquals(1, Command.workoutMode);
    }

    @Test
    void executeUserCommand_invalidWorkoutIndexGreaterThanWorkoutsSize_exceptionThrown() {
        EnterWorkoutCommand c = new EnterWorkoutCommand(3);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, ui, storage));
    }

    @Test
    void executeUserCommand_zeroOrNegativeIndex_exceptionThrown() {
        EnterWorkoutCommand c1 = new EnterWorkoutCommand(0);
        EnterWorkoutCommand c2 = new EnterWorkoutCommand(-4);
        assertThrows(GetJackDException.class, () -> c1.executeUserCommand(workoutList, ui, storage));
        assertThrows(GetJackDException.class, () -> c2.executeUserCommand(workoutList, ui, storage));
    }
}