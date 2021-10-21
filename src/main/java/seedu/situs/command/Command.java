package seedu.situs.command;

import seedu.situs.exceptions.DukeException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents an interface for command classes.
 */
public abstract class Command {

    public long getNumDaysBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }

    public int getIngredientIndex(String ingredientName) {
        //Todo: fill in as a Search
        return 0;
    }

    /**
     * Executes the command.
     *
     * @return the result message if success
     * @throws DukeException if the command cannot be executed normally
     */
    public abstract String run() throws DukeException;
}
