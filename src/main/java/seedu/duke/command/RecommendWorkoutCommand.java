package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


public class RecommendWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "recommend";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Provides a given set of workouts with exercises.\n"
            + "Format: recommend [beginner/intermediate/pro]\n"
            + "\tParameters: KEYWORD\n"
            + "\tExample: " + COMMAND_WORD + " beginner";
    public static final String MESSAGE_SUCCESS = "Recommended workout created: %s";

    private final Workout toCreate;
    private Exercise[] toAdd;

    public RecommendWorkoutCommand(String workoutDescription, String[] exerciseDescription) {
        this.toCreate = new Workout(workoutDescription);
        for (int i = 0; i < exerciseDescription.length; i++) {
            this.toAdd = new Exercise[i];
        }
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        workouts.addWorkout(toCreate);
        toCreate.recommendExercise(toAdd);

        ui.showToUser(String.format(MESSAGE_SUCCESS, toCreate));

        String jsonString = storage.convertToJson(workouts);
        storage.saveData(jsonString);
    }
}
