package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class RecommendWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "recommend";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Provides a given set of workouts with exercises.\n"
            + "Format: recommend [beginner/intermediate/pro]\n"
            + "\tParameters: KEYWORD\n"
            + "\tExample: " + COMMAND_WORD + " beginner";

    private final String workoutLevel;

    public RecommendWorkoutCommand(String workoutLevel) {
        this.workoutLevel = workoutLevel;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        ui.showRecommendedWorkouts(workoutLevel);
    }
}
