package expiryeliminator.parser.argparser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public class ExpiryDateParser extends SingleArgParser<LocalDate> {
    private static final String MESSAGE_INVALID_DATE = "Expiry must be a valid date";

    public LocalDate parse(String expiryDateString) throws InvalidArgFormatException {
        checkArgNotBlank(expiryDateString, "Expiry date cannot be blank.");
        try {
            return LocalDate.parse(expiryDateString);
        } catch (DateTimeParseException e) {
            throw new InvalidArgFormatException(MESSAGE_INVALID_DATE);
        }
    }
}
