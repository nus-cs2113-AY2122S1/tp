package seplanner.exceptions;

import seplanner.ui.UiStorage;

/**
 * Thrown when an invalid module is found in the user's selected module list stored in the file
 */
public class InvalidModuleException extends StorageException {
    public InvalidModuleException() {
        super(UiStorage.getInvalidModuleMessage());
    }
}
