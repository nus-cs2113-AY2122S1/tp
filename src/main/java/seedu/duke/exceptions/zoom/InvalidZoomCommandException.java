package seedu.duke.exceptions.zoom;

import seedu.duke.exceptions.ClickException;
import seedu.duke.ui.Ui;

public class InvalidZoomCommandException extends ClickException {
    public InvalidZoomCommandException() {
        super("Invalid Zoom Suffix");
    }
}
