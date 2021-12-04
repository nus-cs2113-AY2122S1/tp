package seedu.duke.logic.commands;

import seedu.duke.data.item.Item;
import seedu.duke.data.item.exceptions.ItemNotFoundInBankException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author xingjie99

/**
 * Represent the command that when executed, adds all recurring Exercise items to the FutureExerciseList.
 */
public class AddRecurringExerciseCommand extends Command {
    public static final String MESSAGE_INVALID_DATES = "Your start date %s should not be later than your end date %s";
    public static final String MESSAGE_INVALID_FUTURE_DATES = "Make sure that your start date (%s) "
            + "and end date (%s) are in the future";
    public static final String MESSAGE_NO_EXERCISE_ADDED = "Day(s) not present between %s and %s";
    public static final String MESSAGE_SUCCESS = "Recurring exercise item for the future has been added!"
            + CommandMessages.LS + "You can view your upcoming exercises by typing "
            + CommandMessages.QUOTATION + Command.COMMAND_WORD_VIEW + " " + Command.COMMAND_PREFIX_UPCOMING_EXERCISE
            + Command.COMMAND_PREFIX_DELIMITER  + CommandMessages.QUOTATION + "!";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_RECURRING,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_START_DATE,
            COMMAND_PREFIX_END_DATE,
            COMMAND_PREFIX_DAY_OF_THE_WEEK
    };

    private final String description;
    private Integer calories;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ArrayList<Integer> dayOfTheWeek;

    private static Logger logger = Logger.getLogger(AddRecurringExerciseCommand.class.getName());

    public AddRecurringExerciseCommand(String description, Integer calories, LocalDate startDate,
                                       LocalDate endDate, ArrayList<Integer> dayOfTheWeek) {
        this.description = description;
        this.calories = calories;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    @Override
    public CommandResult execute() {
        if (this.startDate.isAfter(this.endDate)) {
            logger.log(Level.FINE, "Start date is after end date");
            return new CommandResult(String.format(MESSAGE_INVALID_DATES,
                    this.startDate.format(CommandMessages.DATE_FORMATTER),
                    this.endDate.format(CommandMessages.DATE_FORMATTER)));
        } else if (endDate.isAfter(LocalDate.now().plusYears(CommandMessages.ONE_YEAR))) {
            return new CommandResult(CommandMessages.MESSAGE_RECURRING_EXERCISE_NOT_WITHIN_ONE_YEAR);
        }

        if (calories == null) {
            try {
                this.calories = super.exerciseBank.findCalorie(this.description);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(
                        CommandMessages.MESSAGE_INVALID_EXERCISE_NOT_IN_BANK, this.description));
            }
        } else if (calories < Item.LOWEST_CALORIE || calories > Item.HIGHEST_CALORIE) {
            logger.log(Level.FINE, "Detected impossible calorie value");
            return new CommandResult(CommandMessages.MESSAGE_INVALID_CALORIES);
        }
        assert this.endDate.isAfter(this.startDate) : "End date is after start date";
        if (!this.startDate.isAfter(LocalDate.now())) {
            logger.log(Level.FINE, "Recurring exercises are for future only");
            return new CommandResult(String.format(MESSAGE_INVALID_FUTURE_DATES,
                    this.startDate.format(CommandMessages.DATE_FORMATTER),
                    this.endDate.format(CommandMessages.DATE_FORMATTER)));
        }
        assert this.startDate.isAfter(LocalDate.now()) : "Start and end dates are in the future";
        int numberOfFutureExercises = futureExerciseItems.getSize();
        futureExerciseItems.addRecurringExercises(this.description, this.calories,
                this.startDate, this.endDate, this.dayOfTheWeek);
        if (futureExerciseItems.getSize() == numberOfFutureExercises) {
            return new CommandResult(String.format(MESSAGE_NO_EXERCISE_ADDED,
                    this.startDate.format(CommandMessages.DATE_FORMATTER),
                    this.endDate.format(CommandMessages.DATE_FORMATTER)));
        }

        logger.log(Level.FINE, "Recurring exercise is successfully added");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}



