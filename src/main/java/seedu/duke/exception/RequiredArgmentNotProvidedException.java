package seedu.duke.exception;

import seedu.duke.task.TypeEnum;

public class RequiredArgmentNotProvidedException extends Exception {
    private static String MESSAGE = "Required argument '%s' was not provided when creating new %s.";

    public RequiredArgmentNotProvidedException(String argument, String task) {
        super(String.format(MESSAGE, argument, task));
    }
}
