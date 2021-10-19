package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;
import seedu.duke.exception.GetJackDException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses and processes input for the create command.
 */
public class CreateWorkoutParser extends Parser {

    public CreateWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    /**
     * Gets arguments required for a workout, such as workoutIndex, exerciseName, sets and reps.
     * commandArgs passed in as [workoutName], [deadline]
     * @param commandArgs user input without the command word.
     * @return string array containing workoutName and deadline
     * @throws GetJackDException thrown if any of the above-mentioned arguments are empty.
     */
    static String[] getWorkoutArgs(String commandArgs) throws GetJackDException {
        if (!commandArgs.contains(PARAMETER_SEPARATOR)) {
            throw new GetJackDException("Invalid format for create workout. No deadline provided!");
        }

        String[] arguments = commandArgs.split(PARAMETER_SEPARATOR);
        String workoutName = arguments[0];
        String deadline = arguments[1];

        String[] workoutArgs = new String[]{workoutName, deadline};
        for (String s : workoutArgs) {
            assert (!s.contains(PARAMETER_SEPARATOR));
        }

        return workoutArgs;
    }

    private Command prepareCreateWorkout(String commandArgs) {
        try {
            String[] workoutArgs = getWorkoutArgs(commandArgs);
            String workoutName = workoutArgs[0].trim();
            String deadline = workoutArgs[1];
            LocalDate deadlineDate = LocalDate.parse(deadline);
            return new CreateWorkoutCommand(workoutName, deadlineDate);
        } catch (GetJackDException | DateTimeParseException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + CreateWorkoutCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareCreateWorkout(commandArgs);
    }
}
