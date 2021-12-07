package seedu.duke.exceptions.storage;

//@@author nvbinh15

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

/**
 * A class that represents exception thrown when there is an error with the storage files.
 */
public class StorageException extends ClickException {

    public StorageException() {
        super(Messages.PRINT_STORAGE_ERROR);
    }
}
