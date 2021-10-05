package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.ui.Ui;

public class CreateWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = "create: Creates a new workout. \n"
            +"\tParameters: w/WORKOUT_NAME\n"
            +"\tExample: create w/ab workout\n";
    public static final String MESSAGE_SUCCESS = "New workout added: %1$s";
    private final Workout toCreate;

    public CreateWorkoutCommand(String description) {
        this.toCreate = new Workout(description);
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        workouts.addWorkout(toCreate);
        System.out.println(String.format(MESSAGE_SUCCESS, toCreate));
    }
}