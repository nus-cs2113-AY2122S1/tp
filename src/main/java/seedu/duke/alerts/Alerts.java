package seedu.duke.alerts;

import seedu.duke.command.ExpireAtStartupCommand;
import seedu.duke.exceptions.DukeException;

public class Alerts {
    public String getAlerts() {
        // TODO: add the string with getLowStockAlerts() later
        String resultMsg = getExpiryAlerts();
        return resultMsg;
    }

    private String getExpiryAlerts() {
        try {
            String resultMsg = new ExpireAtStartupCommand().run();
            return resultMsg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void getLowStockAlerts() {

    }
}
