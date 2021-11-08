package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.storage.models.WorkoutListModel;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineWorkoutTest {
    private Workout testExercises = new DeadlineWorkout("Legs", LocalDate.now());
    private WorkoutListModel workoutListModel = new WorkoutListModel();

    @Test
    public void convertToWorkoutStorageModel_void_expectConvertedToStorageModel() {
        testExercises.addExercise(new Exercise("Test 1", 3, 8));
        WorkoutListModel.clearWorkoutListModel();
        testExercises.convertToWorkoutStorageModel();
        assertEquals(1, workoutListModel.getWorkouts().size());
        assertEquals("Legs", workoutListModel.getWorkouts().get(0).getWorkoutName());
        assertEquals("8", workoutListModel.getWorkouts().get(0).getExercises().get(0).getReps());
    }

}