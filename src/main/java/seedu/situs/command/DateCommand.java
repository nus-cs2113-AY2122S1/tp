package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.localtime.CurrentDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to fetch and update the system's date.
 */
public class DateCommand extends Command {
    private String date;
    private LocalDate formattedDate;

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public String run() throws SitusException {
        String resultMsg;

        if (date.length() <= 0) {
            resultMsg = "Current session date is: " + CurrentDate.getCurrentFormattedDate();
            return resultMsg;
        }

        try {
            formattedDate = LocalDate.parse(date, dateFormat);
            CurrentDate.overwriteCurrentDate(formattedDate);

            resultMsg = "The current session date has been changed to " + formattedDate.format(dateFormat);
            return resultMsg;
        } catch (DateTimeParseException e) {
            throw new SitusException("Please write the date in this format: yyyy/mm/dd\n"
                + "e.g 14/10/2021");
        }
    }
}
