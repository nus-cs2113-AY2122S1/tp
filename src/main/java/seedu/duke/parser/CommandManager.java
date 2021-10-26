package seedu.duke.parser;


import seedu.duke.command.Command;
import seedu.duke.command.exercise.EditExerciseCommand;
import seedu.duke.command.misc.ClearCommand;
import seedu.duke.command.workout.EnterWorkoutCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.workout.ExitWorkoutCommand;
import seedu.duke.command.misc.HelpCommand;
import seedu.duke.command.workout.RecommendWorkoutCommand;
import seedu.duke.command.misc.SearchCommand;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.command.exercise.DisplayExercisesCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.command.exercise.RemoveExerciseCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;
import seedu.duke.command.workout.ListWorkoutsCommand;

//@@author JMattChiam

/**
 * Generates Commands from user input.
 */
public class CommandManager {

    /**
     * Converts raw user input string to a command.
     *
     * @param userInputString raw user input
     * @return command to execute
     */
    public Command generateCommand(String userInputString) {
        Parser parser;
        String commandType = Parser.getCommandType(userInputString);

        switch (commandType) {
        case CreateWorkoutCommand.COMMAND_WORD:
            parser = new CreateWorkoutParser(userInputString);
            break;
        case DeleteWorkoutCommand.COMMAND_WORD:
            parser = new DeleteWorkoutParser(userInputString);
            break;
        case RecommendWorkoutCommand.COMMAND_WORD:
            parser = new RecommendWorkoutParser(userInputString);
            break;
        case DisplayExercisesCommand.COMMAND_WORD:
            parser = new DisplayExerciseParser(userInputString);
            break;
        case AddExerciseCommand.COMMAND_WORD:
            parser = new AddExerciseParser(userInputString);
            break;
        case RemoveExerciseCommand.COMMAND_WORD:
            parser = new RemoveExerciseParser(userInputString);
            break;
        case MarkExerciseAsDoneCommand.COMMAND_WORD:
            parser = new MarkExerciseAsDoneParser(userInputString);
            break;
        case EditExerciseCommand.COMMAND_WORD:
            parser = new EditExerciseParser(userInputString);
            break;
        case HelpCommand.COMMAND_WORD:
            parser = new HelpParser(userInputString);
            break;
        case EnterWorkoutCommand.COMMAND_WORD:
            parser = new EnterWorkoutParser(userInputString);
            break;
        case ExitWorkoutCommand.COMMAND_WORD:
            return new ExitWorkoutCommand();
        case SearchCommand.COMMAND_WORD:
            parser = new SearchParser(userInputString);
            break;
        case ClearCommand.COMMAND_WORD:
            parser = new ClearParser(userInputString);
            break;
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListWorkoutsCommand.COMMAND_WORD:
            return new ListWorkoutsCommand();
        default:
            return new IncorrectCommand("Invalid Command");
        }
        return parser.parseInput();
    }
}
