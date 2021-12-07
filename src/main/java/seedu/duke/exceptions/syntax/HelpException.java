package seedu.duke.exceptions.syntax;

import seedu.duke.Click;
import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class HelpException extends ClickException {
    public HelpException() {
        super(Messages.PRINT_ONLY_HELP);
    }
}
