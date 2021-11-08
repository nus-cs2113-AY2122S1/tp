package seedu.duke.command.workout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Exercise;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.StorageException;
import seedu.duke.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteWorkoutCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException, StorageException {
        createOneWorkoutWithOneExercise();
        storage = new Storage();
    }

    // creates new workout "workout" with exercise "blah" and adds it to workoutList
    private void createOneWorkoutWithOneExercise() {
        Exercise exercise = new Exercise("blah", 10, 30);
        Workout workout = new Workout("workout");
        workout.addExercise(exercise);
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validWorkoutIndex_workoutDeleted() throws GetJackDException {
        int initialWorkoutListSize = workoutList.getAllWorkouts().size();
        DeleteWorkoutCommand c = new DeleteWorkoutCommand(1);
        c.executeUserCommand(workoutList, storage);
        assertEquals(initialWorkoutListSize - 1, 0);
    }

    @Test
    void executeUserCommand_invalidWorkoutIndex_exceptionThrown() {
        DeleteWorkoutCommand c = new DeleteWorkoutCommand(3);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }
}