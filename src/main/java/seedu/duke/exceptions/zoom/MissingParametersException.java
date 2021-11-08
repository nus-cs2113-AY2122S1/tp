package seedu.duke.exceptions.zoom;

import seedu.duke.exceptions.ClickException;
import seedu.duke.ui.Ui;

public class MissingParametersException extends ClickException {
    public MissingParametersException() {
        super("Missing Parameters");
    }
}
