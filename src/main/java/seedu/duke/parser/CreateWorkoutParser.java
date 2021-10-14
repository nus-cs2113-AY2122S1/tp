package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;


public class CreateWorkoutParser extends Parser {

    public CreateWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareCreateWorkout(String commandArgs) {
        if (commandArgs.contains(WORKOUT_KEYWORD)) {
            String workoutName = commandArgs.replace(WORKOUT_KEYWORD, "").trim();
            if (workoutName.isEmpty()) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND + CreateWorkoutCommand.MESSAGE_USAGE);
            }
            return new CreateWorkoutCommand(workoutName);
        }
        return new IncorrectCommand(MESSAGE_INVALID_COMMAND + CreateWorkoutCommand.MESSAGE_USAGE);
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareCreateWorkout(commandArgs);
    }
}
