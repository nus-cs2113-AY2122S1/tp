package seedu.duke.exceptions.zoom;

import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.Messages.PRINT_INVALID_ZOOM_DATA_PATH;

public class InvalidZoomDataPath extends ClickException {
    public InvalidZoomDataPath() {
        super(PRINT_INVALID_ZOOM_DATA_PATH);
    }
}
