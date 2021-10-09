package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public class QuantityParser extends MultipleArgParser<Integer> {
    private static final String MESSAGE_INVALID_NUMBER = "Quantity must be a valid number";

    @Override
    public Integer parse(String quantityString) throws InvalidArgFormatException {
        checkArgNotBlank(quantityString, "Quantity cannot be blank.");
        try {
            return Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new InvalidArgFormatException(MESSAGE_INVALID_NUMBER);
        }
    }
}
