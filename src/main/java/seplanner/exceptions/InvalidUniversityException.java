package seplanner.exceptions;

import seplanner.ui.UiStorage;

/**
 * Thrown when an invalid university is found in the user's selected university list stored in the file
 */
public class InvalidUniversityException extends StorageException {
    public InvalidUniversityException() {
        super(UiStorage.getInvalidUniversityMessage());
    }
}
