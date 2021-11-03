package seedu.duke.data;

import seedu.duke.storage.models.WorkoutListModel;
import seedu.duke.storage.models.WorkoutModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static seedu.duke.data.WorkoutList.LOGGER;

//@@author jonathanmui4

/**
 * A deadline workout is a workout with a deadline set by the user.
 */
public class DeadlineWorkout extends Workout {

    private final String deadline;
    private final LocalDate deadlineDate;

    /**
     * Default Constructor.
     *
     * @param workoutName  is the name that the user gives to the workout routine
     * @param deadlineDate Workout deadline in the form of yyyy-mm-dd
     */
    public DeadlineWorkout(String workoutName, LocalDate deadlineDate) {
        super(workoutName);
        assert deadlineDate != null;
        this.deadlineDate = deadlineDate;
        deadline = deadlineDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public String getDeadlineString() {
        return deadline;
    }

    /**
     * Converts the object to a String.
     *
     * @return name of workout routine
     */
    @Override
    public String toString() {
        assert !deadline.isEmpty();
        return getWorkoutName() + " finish by: " + deadline;
    }

    /**
     * Loops through all the exercise and constructs corresponding ExerciseModel objects, adding together
     * to form a WorkoutModel object to be added to the WorkoutListModel Object.
     * This is done as WorkoutModel objects are easily convertible to JSONStrings for storage.
     */
    @Override
    public void convertToWorkoutStorageModel() {
        LOGGER.info("Generating WorkoutModel with deadline");
        WorkoutModel workoutModel = new WorkoutModel(workoutName, deadlineDate.toString());

        for (Exercise exercise : exercises) {
            exercise.convertToExerciseStorageModel(workoutModel);
        }

        WorkoutListModel.addToWorkoutListModel(workoutModel);
    }
}
