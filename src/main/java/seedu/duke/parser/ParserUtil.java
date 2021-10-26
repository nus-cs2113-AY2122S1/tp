package seedu.duke.parser;

import seedu.duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParserUtil {

    public static int parseMonth(String monthString) throws NumberFormatException {
        return Integer.parseInt(monthString.trim());
    }

    public static int parseIndex(String indexString) throws NumberFormatException {
        return Integer.parseInt(indexString.trim()) - 1;
    }

    public static String parseDescription(String descString, boolean isCompulsory) throws EmptyDescriptionException {
        String description = descString.trim();
        if (description.equals("") && isCompulsory) {
            throw new EmptyDescriptionException();
        }
        return description;
    }

    public static double parseAmount(String amountString, boolean isCompulsory) throws NumberFormatException {
        try {
            return Integer.parseInt(amountString.trim());
        } catch (NumberFormatException e) {
            if (isCompulsory) {
                throw new NumberFormatException();
            }
            return 0.00;
        }
    }

    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString.trim());
        } catch (DateTimeParseException e) {
            return LocalDate.now();
        }
    }
}
