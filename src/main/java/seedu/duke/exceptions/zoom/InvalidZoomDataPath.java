package seedu.duke.exceptions.zoom;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;
import seedu.duke.ui.Ui;

public class InvalidZoomDataPath extends ClickException {
    public InvalidZoomDataPath() {
        super(Messages.PRINT_INVALID_ZOOM_DATA_PATH);
    }
}
