package seplanner.exceptions;

import seplanner.ui.UiStorage;

/**
 * Thrown when an invalid mapping is found in the selected university list stored in the file.
 */
public class InvalidMappingException extends StorageException {
    public InvalidMappingException() {
        super(UiStorage.getInvalidMappingMessage());
    }
}
