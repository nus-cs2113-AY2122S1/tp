package seedu.duke.exceptions.zoom;

import seedu.duke.exceptions.ClickException;
import seedu.duke.ui.Ui;

public class ModuleNotFoundException extends ClickException {
    public ModuleNotFoundException() {
        super("Module Not Found");
    }
}
