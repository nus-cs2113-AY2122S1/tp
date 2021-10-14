package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.exception.GetJackDException;

public class MarkExerciseAsDoneParser extends Parser {

    public MarkExerciseAsDoneParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareMarkExerciseAsDone(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);
            int exerciseIndex = parseArgsAsIndex(indices[1]);

            return new MarkExerciseAsDoneCommand(workoutIndex, exerciseIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + MarkExerciseAsDoneCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareMarkExerciseAsDone(commandArgs);
    }
}
