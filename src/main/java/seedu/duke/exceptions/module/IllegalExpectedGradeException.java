package seedu.duke.exceptions.module;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.ExceptionMessages.MESSAGE_ILLEGAL_EXPECTED_GRADE;

//@@author nvbinh15

/**
 * A class that represents exceptions thrown when the expected grade of a module is illegal.
 */
public class IllegalExpectedGradeException extends ClickException {

    /**
     * Class constructor inherited from ClickException.
     */
    public IllegalExpectedGradeException() {
        super(MESSAGE_ILLEGAL_EXPECTED_GRADE);
    }
}
