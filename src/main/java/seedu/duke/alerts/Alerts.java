package seedu.duke.alerts;

import seedu.duke.command.AlertCommand;
import seedu.duke.exceptions.DukeException;

public class Alerts {

    public String getAlerts() {
        try {
            return new AlertCommand().run();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
