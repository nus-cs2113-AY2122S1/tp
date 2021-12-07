package seedu.duke.exceptions.syntax;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

/**
 * Thrown when command entered by user starts with a correct keyword,
 * but full command does not exist.
 *
 * @author ngnigel99
 */
public class InvalidArgumentsException extends ClickException {
    public InvalidArgumentsException() {
        super(Messages.PRINT_FULL_COMMAND_NOT_EXISTS);
    }
}