package seedu.situs.command;

import seedu.situs.exceptions.SitusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents an interface for command classes.
 */
public abstract class Command {

    public long getNumDaysBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }

    /**
     * Executes the command.
     *
     * @return the result message if success
     * @throws SitusException if the command cannot be executed normally
     */
    public abstract String run() throws SitusException;
}
