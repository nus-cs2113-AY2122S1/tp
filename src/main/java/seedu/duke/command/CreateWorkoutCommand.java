package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

public class CreateWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new workout. \n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_NAME\n"
            + "\tExample: create " + WORKOUT_KEYWORD + "ab workout\n";
    public static final String MESSAGE_SUCCESS = "New workout added: %1$s";
    private final Workout toCreate;

    public CreateWorkoutCommand(String description) {
        this.toCreate = new Workout(description);
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        workouts.addWorkout(toCreate);
        ui.showToUser(String.format(MESSAGE_SUCCESS, toCreate));
        String jsonString = storage.convertToJSON(workouts);
        storage.saveData(jsonString);
    }
}