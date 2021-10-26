package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;
import seedu.duke.exception.GetJackDException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//@@author qqkoh

/**
 * Parses and processes input for the create command.
 */
public class CreateWorkoutParser extends Parser {

    public CreateWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    /**
     * Gets arguments required for a workout, such as workoutName and Deadline (if user chose to set it).
     * commandArgs passed in as "[workoutName]" or "[workoutName], [deadline]".
     *
     * @param commandArgs user input without the command word
     * @return string array containing workoutName and deadline (if user chose to set it)
     * @throws GetJackDException thrown if workoutName is empty
     */
    static String[] getWorkoutArgs(String commandArgs) throws GetJackDException {
        String[] workoutArgs = null;
        if (!commandArgs.contains(PARAMETER_SEPARATOR)) {
            String workoutName = commandArgs.trim();
            if (workoutName.length() == 0) {
                throw new GetJackDException(MESSAGE_INVALID_COMMAND + CreateWorkoutCommand.MESSAGE_USAGE);
            }
            workoutArgs = new String[]{workoutName};
        } else {
            String[] arguments = commandArgs.split(PARAMETER_SEPARATOR);
            String workoutName = arguments[0];
            String deadline = arguments[1];

            workoutArgs = new String[]{workoutName, deadline};
        }
        return workoutArgs;
    }

    private Command prepareCreateWorkout(String commandArgs) {
        try {
            String[] workoutArgs = getWorkoutArgs(commandArgs);
            if (workoutArgs.length == 1) {
                return new CreateWorkoutCommand(workoutArgs[0].trim());
            } else {
                String workoutName = workoutArgs[0].trim();
                String deadline = workoutArgs[1];
                LocalDate deadlineDate = LocalDate.parse(deadline);
                return new CreateWorkoutCommand(workoutName, deadlineDate);
            }
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
