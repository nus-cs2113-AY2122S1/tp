package seedu.duke.lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutListTest {

    @Test
    public void addWorkout_randomNewWorkout_sizeIncreasesByOne() {
        WorkoutList testWorkouts = new WorkoutList();
        testWorkouts.addWorkout(new Workout("Test 1"));
        assertEquals(1, testWorkouts.getAllWorkouts().size());
        testWorkouts.addWorkout(new Workout("Test 2"));
        assertEquals(2, testWorkouts.getAllWorkouts().size());
        testWorkouts.addWorkout(new Workout("Test 3"));
        assertEquals(3, testWorkouts.getAllWorkouts().size());
    }
}