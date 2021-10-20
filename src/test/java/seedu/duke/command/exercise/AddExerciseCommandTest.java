package seedu.duke.command.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddExerciseCommandTest {
    private WorkoutList workoutList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    public void setUp() throws GetJackDException {
        createEmptyWorkout();
        storage = new Storage();
        ui = new Ui();
    }

    private void createEmptyWorkout() {
        Workout workout = new Workout("blah", LocalDate.parse("2021-10-18"));
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validDataAddToEmptyWorkout_exerciseAdded() throws GetJackDException {
        int initialSize = workoutList.getWorkout(1).getAllExercises().size();
        AddExerciseCommand c = new AddExerciseCommand(1, "description", 3, 10);
        c.executeUserCommand(workoutList, storage);
        assertEquals(initialSize + 1, workoutList.getWorkout(1).getAllExercises().size());
    }

    @Test
    void executeUserCommand_invalidSetsReps_exceptionThrown() {
        AddExerciseCommand c = new AddExerciseCommand(1, "description", -3, 10);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }
}
