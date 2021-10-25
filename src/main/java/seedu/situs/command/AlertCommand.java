package seedu.situs.command;

import seedu.situs.exceptions.SitusException;

public class AlertCommand extends Command {

    public AlertCommand() {
    }

    @Override
    public String run() throws SitusException {
        String expiryAlerts = new AlertExpiringSoonCommand().run();
        String lowStockAlerts = new AlertLowStockCommand().run();
        return expiryAlerts + '\n' + '\n' + lowStockAlerts;
    }
}
