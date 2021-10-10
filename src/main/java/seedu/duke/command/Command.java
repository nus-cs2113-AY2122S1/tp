package seedu.duke.command;
import seedu.duke.exceptions.DukeException;

/**
 * Represents an interface for command classes.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @return the result message if success
     * @throws DukeException if the command cannot be executed normally
     */
    String run() throws DukeException;
}
