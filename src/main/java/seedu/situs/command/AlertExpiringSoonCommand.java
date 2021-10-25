package seedu.situs.command;

import seedu.situs.exceptions.SitusException;

import seedu.situs.localtime.CurrentDate;

import java.time.LocalDate;


public class AlertExpiringSoonCommand extends Command {

    private static long expiryThreshold = 5;

    public static void setExpiryThreshold(long expiryThreshold) {
        AlertExpiringSoonCommand.expiryThreshold = expiryThreshold;
    }

    @Override
    public String run() throws SitusException {
        LocalDate expiryDateThreshold = CurrentDate.getCurrentDate().plusDays(expiryThreshold);

        return new ExpireCommand(expiryDateThreshold).run();
    }
}
