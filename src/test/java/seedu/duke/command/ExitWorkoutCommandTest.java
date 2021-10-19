package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.workout.EnterWorkoutCommand;
import seedu.duke.command.workout.ExitWorkoutCommand;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitWorkoutCommandTest {
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
        Workout workout = new Workout("workout", LocalDate.parse("2021-10-10"));
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_enterThenExitWorkoutOne_workoutModeChanged() throws GetJackDException {
        EnterWorkoutCommand c = new EnterWorkoutCommand(1);
        c.executeUserCommand(workoutList, ui, storage);
        assertEquals(1, Command.workoutMode);
        ExitWorkoutCommand c2 = new ExitWorkoutCommand();
        c2.executeUserCommand(workoutList, ui, storage);
        assertEquals(0, Command.workoutMode);
    }
}