package seedu.mtracker.commons.error.fileerror;

import seedu.mtracker.commons.error.ErrorMessage;

//@@author williamlamjy
/**
 * The custom exception class that is thrown when storage file instrument sentiment saved is invalid.
 */
public class InvalidSentimentSavedInFileError extends Exception {

    /**
     * Returns the error message to the user stating that sentiment in storage file is invalid.
     *
     * @return A string error message that states the sentiment in storage file is saved is invalid.
     */
    @Override
    public String getMessage() {
        return ErrorMessage.SENTIMENT_FORMATTING_IN_FILE_ERROR;
    }
}
