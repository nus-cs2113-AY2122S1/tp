package expiryeliminator.parser.argparser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses expiry date.
 */
public class ExpiryDateParser extends SingleArgParser<LocalDate> {
    private static final String MESSAGE_INVALID_DATE = "Expiry must be a valid date (yyyy-mm-dd).";

    /**
     * Parses expiry date.
     *
     * @param expiryDateString String to be parsed as an expiry date.
     * @return Parsed expiry date.
     * @throws InvalidArgFormatException If the string is blank or is not a valid date.
     */
    @Override
    public LocalDate parse(String expiryDateString) throws InvalidArgFormatException {
        checkArgNotBlank(expiryDateString, "Expiry date cannot be blank.");
        try {
            return LocalDate.parse(expiryDateString);
        } catch (DateTimeParseException e) {
            throw new InvalidArgFormatException(MESSAGE_INVALID_DATE);
        }
    }
}
