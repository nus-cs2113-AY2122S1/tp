package seedu.duke.exercise;

import org.junit.jupiter.api.Test;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.ExerciseList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ExerciseListTest {

    @Test
    void addExercise_exerciseClassParameter_expectAddInList() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addItem(new Exercise("Jumping Jacks", 100));
        assertEquals("Jumping Jacks (100 cal)", exerciseList.getItem(0).toString());
    }

    @Test
    void deleteExercise_exerciseIndex_expectDeleteFromList() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addItem(new Exercise("Running", 250));
        exerciseList.addItem(new Exercise("Jumping Jacks", 100));
        exerciseList.deleteItem(0, LocalDate.now());
        assertEquals("Jumping Jacks (100 cal)", exerciseList.getItem(0).toString());
    }

    @Test
    void deleteExercise_exerciseIndex_expectCorrectNumberOfTasksLeft() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addItem(new Exercise("Running", 250,
                LocalDate.parse("19-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        exerciseList.addItem(new Exercise("Jumping Jacks", 100,
                LocalDate.parse("19-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        System.out.println(exerciseList.deleteItem(0,
                LocalDate.parse("19-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        assertEquals(1, exerciseList.getSize());
    }

    @Test
    void deleteExercise_invalidIndex_expectException() {
        ExerciseList exerciseList = new ExerciseList();
        assertThrows(IndexOutOfBoundsException.class, () -> exerciseList.deleteItem(0,
                LocalDate.parse("19-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    void totalCalorie_someExercises_expectTotalCalorie() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addItem(new Exercise("Running", 250));
        exerciseList.addItem(new Exercise("Jumping Jacks", 100));
        exerciseList.addItem(new Exercise("Skipping", 200));
        exerciseList.addItem(new Exercise("Swimming", 300));
        assertEquals(850, exerciseList.getTotalCalories());
    }

    @Test
    void sortExerciseList_callSortExerciseListMethod_expectSortedList() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addItem(new Exercise("Running", 250,
                LocalDate.parse("2021-10-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Jumping Jacks", 100,
                LocalDate.parse("2021-10-19", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Skipping", 200,
                LocalDate.parse("2021-10-18", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Swimming", 300,
                LocalDate.parse("2021-10-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.sortList();
        System.out.println(exerciseList.convertToString());
    }

    @Test
    void totalExerciseCaloriesForSingleDate_oneLocalDateInput_expectSumOfCalorieOnThatDay() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addItem(new Exercise("Running", 250,
                LocalDate.parse("2021-10-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Jumping Jacks", 100,
                LocalDate.parse("2021-10-19", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Skipping", 200,
                LocalDate.parse("2021-10-18", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Swimming", 300,
                LocalDate.parse("2021-10-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Jump rope", 453,
                LocalDate.parse("2021-10-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Biking", 420,
                LocalDate.parse("2021-10-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        assertEquals(1173, exerciseList.getTotalCaloriesWithDate(
                LocalDate.parse("17-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    void printExerciseListByOneGivenDate_inputLocalDate_expectExerciseListOfTheDayOnly() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addItem(new Exercise("Running", 250,
                LocalDate.parse("2021-10-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Jumping Jacks", 100,
                LocalDate.parse("2021-10-19", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Skipping", 200,
                LocalDate.parse("2021-10-18", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Swimming", 300,
                LocalDate.parse("2021-10-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Jump rope", 453,
                LocalDate.parse("2021-10-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        exerciseList.addItem(new Exercise("Biking", 420,
                LocalDate.parse("2021-10-17", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        System.out.println(exerciseList.convertToStringBySpecificDate(
                LocalDate.parse("17-10-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }
}
