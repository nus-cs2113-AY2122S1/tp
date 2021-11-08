package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.DeadlineWorkout;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.time.LocalDate;

import static seedu.duke.parser.Parser.PARAMETER_SEPARATOR;

//@@author qqkoh

/**
 * To create a new Workout.
 */
public class CreateWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new workout.\n"
            + "Format: create [workout description] or Format: create [workout description], [deadline]\n"
            + "Parameters:\n"
            + "\tWorkout description - description or name of workout\n"
            + "\tWorkout deadline - deadline of workout (optional)\n"
            + "Example: " + COMMAND_WORD + " abs (if no deadline set)\n"
            + "Example: " + COMMAND_WORD + " abs" + PARAMETER_SEPARATOR + "2021-12-25 (if you want to set a deadline)";

    public static final String MESSAGE_SUCCESS = "New workout created: %s";

    private Workout toCreate;
    private DeadlineWorkout toCreateWithDeadline;

    /**
     * Instantiates object and creates a new workout.
     *
     * @param description description of new workout.
     */
    public CreateWorkoutCommand(String description) {
        this.toCreate = new Workout(description);
    }

    /**
     * Instantiates object and creates a new workout with deadline.
     *
     * @param description description of new workout.
     * @param deadline    deadline of new workout.
     */
    public CreateWorkoutCommand(String description, LocalDate deadline) {
        this.toCreateWithDeadline = new DeadlineWorkout(description, deadline);
    }

    /**
     * Executes create workout command to create a new workout into the workout list.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     * @throws GetJackDException if an error occurs within the storage
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        CommandResult commandResult;
        if (toCreate != null) {
            workouts.addWorkout(toCreate);
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, toCreate));
        } else {
            workouts.addWorkout(toCreateWithDeadline);
            commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, toCreateWithDeadline));
        }
        workouts.sortWorkouts();

        String jsonString = storage.convertToJson(workouts);
        storage.saveData(jsonString);
        return commandResult;
    }
}