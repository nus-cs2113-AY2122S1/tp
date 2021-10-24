package seedu.duke.data;

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
        testWorkouts.addWorkout(new Workout("Test 1"));
        assertEquals(1, testWorkouts.getAllWorkouts().size());
        testWorkouts.addWorkout(new Workout("Test 2"));
        assertEquals(2, testWorkouts.getAllWorkouts().size());
        testWorkouts.addWorkout(new Workout("Test 3"));
        assertEquals(3, testWorkouts.getAllWorkouts().size());
        testWorkouts.getAllWorkouts().clear();
    }

    @Test
    public void addDeadlineWorkout_newDeadlineWorkout_expectWorkoutNameWithDeadline() throws GetJackDException {
        testWorkouts.addWorkout(new DeadlineWorkout("Test 1", LocalDate.parse("2021-12-25")));
        assertEquals("Test 1 finish by: 25 Dec 2021", testWorkouts.getWorkout(1).toString());
        testWorkouts.getAllWorkouts().clear();
    }

    @Test
    public void removeWorkout_indexOfWorkouts_expectSizeDecreasesByOne() throws GetJackDException {
        testWorkouts.addWorkout(new Workout("Test 1"));
        testWorkouts.addWorkout(new Workout("Test 2"));
        testWorkouts.addWorkout(new Workout("Test 3"));
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
        testWorkouts.addWorkout(new Workout("Test 1"));
        testWorkouts.addWorkout(new Workout("Test 2"));
        testWorkouts.addWorkout(new Workout("Test 3"));
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
        testWorkouts.addWorkout(new Workout("Test 1"));
        testWorkouts.addWorkout(new Workout("Test 2"));
        assertThrows(GetJackDException.class, () -> testWorkouts.removeWorkout(-1));
        assertThrows(GetJackDException.class, () -> testWorkouts.removeWorkout(3));
        assertThrows(GetJackDException.class, () -> testWorkouts.removeWorkout(0));
        testWorkouts.getAllWorkouts().clear();
    }

    @Test
    public void sortWorkout_void_expectSortedWorkoutList() throws GetJackDException {
        testWorkouts.addWorkout(new Workout("Test 1"));
        testWorkouts.addWorkout(new DeadlineWorkout("xmas abs", LocalDate.parse("2021-12-25")));
        testWorkouts.addWorkout(new DeadlineWorkout("hello halloween", LocalDate.parse("2021-10-31")));
        testWorkouts.sortWorkouts();
        assertEquals("hello halloween finish by: 31 Oct 2021", testWorkouts.getWorkout(1).toString());
        assertEquals("xmas abs finish by: 25 Dec 2021", testWorkouts.getWorkout(2).toString());
        assertEquals("Test 1", testWorkouts.getWorkout(3).toString());
    }

    @Test
    public void convertAllWorkoutsToStorageModel_void_expectWorkoutsConvertedToStorageModel() {
        testWorkouts.addWorkout(new Workout("Test 1"));
        WorkoutListModel.clearWorkoutListModel();
        testWorkouts.convertAllWorkoutsToStorageModel();
        assertEquals(1, workoutListModel.getWorkouts().size());
        assertEquals("Test 1", workoutListModel.getWorkouts().get(0).getWorkoutName());
        testWorkouts.getAllWorkouts().clear();
    }
}