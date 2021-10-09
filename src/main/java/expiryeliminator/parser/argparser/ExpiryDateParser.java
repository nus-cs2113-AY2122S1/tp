package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public class ExpiryDateParser extends SingleArgParser<String> {

    public String parse(String expiryDateString) throws InvalidArgFormatException {
        checkArgNotBlank(expiryDateString, "Expiry date cannot be blank.");
        return expiryDateString;
    }
}
