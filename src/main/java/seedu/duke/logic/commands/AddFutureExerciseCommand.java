package seedu.duke.logic.commands;

import seedu.duke.data.item.exceptions.ItemNotFoundInBankException;
import seedu.duke.data.item.exercise.Exercise;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author xingjie99

/**
 * Represents the command that when executed, adds an Exercise item to the FutureExerciseList.
 */
public class AddFutureExerciseCommand extends Command {
    public static final String MESSAGE_SUCCESS = "An exercise item for the future has been added:"
            + CommandMessages.INDENTED_LS + "%s";

    private static Logger logger = Logger.getLogger(AddFutureExerciseCommand.class.getName());

    private final String description;
    private Integer calories;
    private final LocalDate date;

    public AddFutureExerciseCommand(String description, Integer calories, LocalDate date) {
        this.description = description;
        this.calories = calories;
        this.date = date;
    }

    @Override
    public CommandResult execute() {
        final Exercise exercise;
        if (date.isAfter(LocalDate.now().plusYears(CommandMessages.ONE_YEAR))) {
            return new CommandResult(CommandMessages.MESSAGE_EXERCISE_NOT_WITHIN_ONE_YEAR);
        }
        if (calories == null) {
            try {
                this.calories = super.exerciseBank.findCalorie(this.description);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(
                        CommandMessages.MESSAGE_INVALID_EXERCISE_NOT_IN_BANK, this.description));
            }
        }

        exercise = new Exercise(this.description, this.calories, this.date);
        if (!exercise.isValid()) {
            logger.log(Level.FINE, "Detected impossible calorie burnt for the exercise.");
            return new CommandResult(CommandMessages.MESSAGE_INVALID_CALORIES);
        }
        assert exercise.getCalories() > 0 : "Exercise calorie is valid";
        super.futureExerciseItems.addItem(exercise);
        logger.log(Level.FINE, "Exercise is successfully added");
        return new CommandResult(String.format(MESSAGE_SUCCESS, exercise.toStringWithDate()));
    }
}

