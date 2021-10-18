package seedu.duke.lists;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.GetJackDException;
import seedu.duke.storage.models.WorkoutListModel;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorkoutListTest {
    private WorkoutList testWorkouts = new WorkoutList();
    private WorkoutListModel workoutListModel = new WorkoutListModel();

    @Test
    public void addWorkout_newWorkouts_expectSizeIncreasesByOne() {
        testWorkouts.addWorkout(new Workout("Test 1", LocalDate.parse("2021-10-10")));
        assertEquals(1, testWorkouts.getAllWorkouts().size());
        testWorkouts.addWorkout(new Workout("Test 2", LocalDate.parse("2021-10-10")));
        assertEquals(2, testWorkouts.getAllWorkouts().size());
        testWorkouts.addWorkout(new Workout("Test 3", LocalDate.parse("2021-10-10")));
        assertEquals(3, testWorkouts.getAllWorkouts().size());
        testWorkouts.getAllWorkouts().clear();
    }

    @Test
    public void removeWorkout_indexOfWorkouts_expectSizeDecreasesByOne() throws GetJackDException {
        testWorkouts.addWorkout(new Workout("Test 1", LocalDate.parse("2021-10-10")));
        testWorkouts.addWorkout(new Workout("Test 2", LocalDate.parse("2021-10-10")));
        testWorkouts.addWorkout(new Workout("Test 3", LocalDate.parse("2021-10-10")));
        testWorkouts.removeWorkout(3);
        assertEquals(2, testWorkouts.getAllWorkouts().size());
        testWorkouts.removeWorkout(1);
        assertEquals(1, testWorkouts.getAllWorkouts().size());
        testWorkouts.removeWorkout(1);
        assertEquals(0, testWorkouts.getAllWorkouts().size());
        testWorkouts.getAllWorkouts().clear();
    }

    @Test
    public void removeWorkout_indexOfWorkouts_expectCorrectWorkoutReturned() throws GetJackDException {
        testWorkouts.addWorkout(new Workout("Test 1", LocalDate.parse("2021-10-10")));
        testWorkouts.addWorkout(new Workout("Test 2", LocalDate.parse("2021-10-10")));
        testWorkouts.addWorkout(new Workout("Test 3", LocalDate.parse("2021-10-10")));
        Workout test3 = testWorkouts.removeWorkout(3);
        Workout test1 = testWorkouts.removeWorkout(1);
        Workout test2 = testWorkouts.removeWorkout(1);
        assertEquals("Test 1", test1.getWorkoutName());
        assertEquals("Test 2", test2.getWorkoutName());
        assertEquals("Test 3", test3.getWorkoutName());
        testWorkouts.getAllWorkouts().clear();
    }

    @Test
    public void removeWorkout_invalidIndex_expectGetJackDExceptionThrown() {
        testWorkouts.addWorkout(new Workout("Test 1", LocalDate.parse("2021-10-10")));
        testWorkouts.addWorkout(new Workout("Test 2", LocalDate.parse("2021-10-10")));
        assertThrows(GetJackDException.class, () -> testWorkouts.removeWorkout(-1));
        assertThrows(GetJackDException.class, () -> testWorkouts.removeWorkout(3));
        assertThrows(GetJackDException.class, () -> testWorkouts.removeWorkout(0));
        testWorkouts.getAllWorkouts().clear();
    }

    @Test
    public void convertAllWorkoutsToStorageModel_void_expectWorkoutsConvertedToStorageModel() {
        testWorkouts.addWorkout(new Workout("Test 1", LocalDate.parse("2021-10-10")));
        WorkoutListModel.clearWorkoutListModel();
        testWorkouts.convertAllWorkoutsToStorageModel();
        assertEquals(1, workoutListModel.getWorkouts().size());
        assertEquals("Test 1", workoutListModel.getWorkouts().get(0).getWorkoutName());
        testWorkouts.getAllWorkouts().clear();
    }
}