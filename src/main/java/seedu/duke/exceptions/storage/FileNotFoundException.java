package seedu.duke.exceptions.storage;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class FileNotFoundException extends ClickException {
    public FileNotFoundException() {
        super(Messages.PRINT_FILE_NOT_FOUND);
    }
}
