package seedu.situs.command;

import seedu.situs.Situs;
import seedu.situs.exceptions.SitusException;

import seedu.situs.localtime.CurrentDate;
import seedu.situs.storage.Storage;

import java.io.IOException;
import java.time.LocalDate;

//@@author mudkip8

public class AlertExpiringSoonCommand extends Command {

    private static long expiryThreshold = 5;

    public AlertExpiringSoonCommand() throws SitusException {
        try {
            expiryThreshold = new Storage().loadExpiryThreshold();
        } catch (IOException | SitusException e) {
            throw new SitusException(e.getMessage());
        }
    }

    public static long getExpiryThreshold() {
        return expiryThreshold;
    }

    public static void setExpiryThreshold(long expiryThreshold) throws IOException {
        AlertExpiringSoonCommand.expiryThreshold = expiryThreshold;
        new Storage().writeThresholdData();
    }

    @Override
    public String run() throws SitusException {
        LocalDate expiryDateThreshold = CurrentDate.getCurrentDate().plusDays(expiryThreshold);

        return new ExpireCommand(expiryDateThreshold).run();
    }
}
