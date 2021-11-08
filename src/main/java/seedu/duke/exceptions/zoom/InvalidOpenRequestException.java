package seedu.duke.exceptions.zoom;

import seedu.duke.exceptions.ClickException;
import seedu.duke.ui.Ui;

public class InvalidOpenRequestException extends ClickException {
    public InvalidOpenRequestException() {
        Ui.printMessage("Invalid link");
    }
}
