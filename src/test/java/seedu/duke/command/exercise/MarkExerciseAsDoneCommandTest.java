package seedu.duke.command.exercise;

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


class MarkExerciseAsDoneCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException, StorageException {
        createOneWorkoutWithOneExercise();
        storage = new Storage();
    }

    private void createOneWorkoutWithOneExercise() {
        Exercise exercise = new Exercise("blah", 10, 30);
        Workout workout = new Workout("workout");
        workout.addExercise(exercise);
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validWorkoutIndexExerciseIndex_exerciseDone() throws GetJackDException {
        MarkExerciseAsDoneCommand c = new MarkExerciseAsDoneCommand(1, 1);
        c.executeUserCommand(workoutList, storage);
        assertEquals(workoutList.getWorkout(1).getExercise(1).getIsDone(), true);
    }

    @Test
    void executeUserCommand_invalidExerciseIndex_exceptionThrown() {
        MarkExerciseAsDoneCommand c = new MarkExerciseAsDoneCommand(1, 3);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }

    @Test
    void executeUserCommand_invalidWorkoutIndex_exceptionThrown() {
        MarkExerciseAsDoneCommand c = new MarkExerciseAsDoneCommand(5, 3);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }

}