package seedu.duke.exceptions.syntax;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class ArgumentsNotFoundException extends ClickException {
    public ArgumentsNotFoundException() {
        super(Messages.PRINT_MISSING_ARGUMENT);
    }

    public ArgumentsNotFoundException(String value) {
        super(Messages.PRINT_MISSING_SPECIFIC_ARGUMENT + value);
    }
}
