package seedu.duke.lists;

import org.junit.jupiter.api.Test;
import seedu.duke.exercises.Exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutTest {

    @Test
    public void addExercise_randomNewExercise_sizeIncreaseByOne() {
        Workout testExercises = new Workout("Legs");
        testExercises.addExercise(new Exercise("Test 1",3,8));
        assertEquals(1, testExercises.getAllExercises().size());
        testExercises.addExercise(new Exercise("Test 2",3,8));
        assertEquals(2, testExercises.getAllExercises().size());
        testExercises.addExercise(new Exercise("Test 3",2,10));
        assertEquals(3, testExercises.getAllExercises().size());
    }
}