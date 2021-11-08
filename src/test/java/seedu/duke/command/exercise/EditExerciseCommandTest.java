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

class EditExerciseCommandTest {
    private WorkoutList workoutList;
    private Storage storage;

    @BeforeEach
    public void setUp() throws GetJackDException, StorageException {
        createEmptyWorkout();
        storage = new Storage();
    }

    private void createEmptyWorkout() {
        Workout workout = new Workout("blah");
        Exercise exercise = new Exercise("blah", 10, 30);
        Exercise exerciseTwo = new Exercise("blahTwo", 5, 15);
        workout.addExercise(exercise);
        workout.addExercise(exerciseTwo);
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void executeUserCommand_validDataToEditDescription_exerciseDescriptionEdited() throws GetJackDException {
        EditExerciseCommand c = new EditExerciseCommand(1, 1, "description", 3, 10);
        EditExerciseCommand c2 = new EditExerciseCommand(2, 1, "descriptionTwo", 2, 5);
        c.executeUserCommand(workoutList, storage);
        c2.executeUserCommand(workoutList, storage);
        assertEquals(workoutList.getWorkout(1).getExercise(1).getDescription(), "description");
        assertEquals(workoutList.getWorkout(1).getExercise(2).getDescription(), "descriptionTwo");
    }

    @Test
    void executeUserCommand_validDataToEditSets_exerciseSetsEdited() throws GetJackDException {
        EditExerciseCommand c = new EditExerciseCommand(1, 1, "description", 3, 10);
        EditExerciseCommand c2 = new EditExerciseCommand(2, 1, "descriptionTwo", 2, 5);
        c.executeUserCommand(workoutList, storage);
        c2.executeUserCommand(workoutList, storage);
        assertEquals(workoutList.getWorkout(1).getExercise(1).getSets(), 3);
        assertEquals(workoutList.getWorkout(1).getExercise(2).getSets(), 2);
    }

    @Test
    void executeUserCommand_validDataToEditReps_exerciseRepsEdited() throws GetJackDException {
        EditExerciseCommand c = new EditExerciseCommand(1, 1, "description", 3, 10);
        EditExerciseCommand c2 = new EditExerciseCommand(2, 1, "descriptionTwo", 2, 5);
        c.executeUserCommand(workoutList, storage);
        c2.executeUserCommand(workoutList, storage);
        assertEquals(workoutList.getWorkout(1).getExercise(1).getReps(), 10);
        assertEquals(workoutList.getWorkout(1).getExercise(2).getReps(), 5);
    }

    @Test
    void executeUserCommand_invalidWorkoutIndex_exceptionThrown() {
        EditExerciseCommand c = new EditExerciseCommand(1, 10, "description", 3, 10);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }

    @Test
    void executeUserCommand_invalidExerciseIndex_exceptionThrown() {
        EditExerciseCommand c = new EditExerciseCommand(10, 1, "description", 3, 10);
        assertThrows(GetJackDException.class, () -> c.executeUserCommand(workoutList, storage));
    }
}