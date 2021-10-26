package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.exercise.EditExerciseCommand;
import seedu.duke.command.misc.HelpCommand;
import seedu.duke.command.workout.RecommendWorkoutCommand;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.command.exercise.DisplayExercisesCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.command.exercise.RemoveExerciseCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;
import seedu.duke.command.workout.ListWorkoutsCommand;

//@@author JMattChiam

/**
 * Parses and processes input for the help command.
 */
public class HelpParser extends Parser {

    public HelpParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareHelpMessage(String commandArgs) {
        switch (commandArgs) {
        case DisplayExercisesCommand.COMMAND_WORD:
            return new HelpCommand(DisplayExercisesCommand.MESSAGE_USAGE_MAIN);
        case ListWorkoutsCommand.COMMAND_WORD:
            return new HelpCommand(ListWorkoutsCommand.MESSAGE_USAGE);
        case RecommendWorkoutCommand.COMMAND_WORD:
            return new HelpCommand(RecommendWorkoutCommand.MESSAGE_USAGE);
        case AddExerciseCommand.COMMAND_WORD:
            return new HelpCommand(AddExerciseCommand.MESSAGE_USAGE_MAIN);
        case CreateWorkoutCommand.COMMAND_WORD:
            return new HelpCommand(CreateWorkoutCommand.MESSAGE_USAGE);
        case DeleteWorkoutCommand.COMMAND_WORD:
            return new HelpCommand(DeleteWorkoutCommand.MESSAGE_USAGE);
        case MarkExerciseAsDoneCommand.COMMAND_WORD:
            return new HelpCommand(MarkExerciseAsDoneCommand.MESSAGE_USAGE_MAIN);
        case RemoveExerciseCommand.COMMAND_WORD:
            return new HelpCommand(RemoveExerciseCommand.MESSAGE_USAGE_MAIN);
        case EditExerciseCommand.COMMAND_WORD:
            return new HelpCommand(EditExerciseCommand.MESSAGE_USAGE_MAIN);
        case ExitCommand.COMMAND_WORD:
            return new HelpCommand(ExitCommand.MESSAGE_USAGE);
        default:
            return new HelpCommand();
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareHelpMessage(commandArgs);
    }
}
