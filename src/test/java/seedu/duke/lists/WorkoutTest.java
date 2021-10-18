package seedu.duke.lists;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.storage.models.WorkoutListModel;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkoutTest {

    private Workout testExercises = new Workout("Legs", LocalDate.parse("2021-10-10"));
    private WorkoutListModel workoutListModel = new WorkoutListModel();

    @Test
    public void addExercise_newExercises_expectSizeIncreaseByOne() {
        testExercises.addExercise(new Exercise("Test 1", 3, 8));
        assertEquals(1, testExercises.getAllExercises().size());
        testExercises.addExercise(new Exercise("Test 2", 3, 8));
        assertEquals(2, testExercises.getAllExercises().size());
        testExercises.addExercise(new Exercise("Test 3", 2, 10));
        assertEquals(3, testExercises.getAllExercises().size());
        testExercises.getAllExercises().clear();
    }

    @Test
    public void removeExercise_indexOfExercises_expectSizeDecreasesByOne() throws GetJackDException {
        testExercises.addExercise(new Exercise("Test 1", 3, 8));
        testExercises.addExercise(new Exercise("Test 2", 3, 8));
        testExercises.addExercise(new Exercise("Test 3", 2, 10));
        testExercises.removeExercise(1);
        assertEquals(2, testExercises.getAllExercises().size());
        testExercises.removeExercise(1);
        assertEquals(1, testExercises.getAllExercises().size());
        testExercises.removeExercise(1);
        assertEquals(0, testExercises.getAllExercises().size());
        testExercises.getAllExercises().clear();
    }

    @Test
    public void removeExercise_indexOfExercises_expectCorrectExerciseReturned() throws GetJackDException {
        testExercises.addExercise(new Exercise("Test 1", 3, 8));
        testExercises.addExercise(new Exercise("Test 2", 4, 11));
        testExercises.addExercise(new Exercise("Test 3", 7, 13));
        Exercise test3 = testExercises.removeExercise(3);
        Exercise test1 = testExercises.removeExercise(1);
        Exercise test2 = testExercises.removeExercise(1);
        assertEquals(3, test1.getSets());
        assertEquals(4, test2.getSets());
        assertEquals(7, test3.getSets());
        testExercises.getAllExercises().clear();
    }

    @Test
    public void removeExercise_invalidIndex_expectGetJackDExceptionThrown() {
        testExercises.addExercise(new Exercise("Test 1", 3, 8));
        testExercises.addExercise(new Exercise("Test 2", 4, 11));
        assertThrows(GetJackDException.class, () -> testExercises.removeExercise(-1));
        assertThrows(GetJackDException.class, () -> testExercises.removeExercise(3));
        assertThrows(GetJackDException.class, () -> testExercises.removeExercise(0));
        testExercises.getAllExercises().clear();
    }

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