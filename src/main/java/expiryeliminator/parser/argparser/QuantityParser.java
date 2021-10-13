package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public class QuantityParser extends MultipleArgParser<Integer> {
    private static final String MESSAGE_INVALID_NUMBER = "Quantity must be a valid number";
    private static final String MESSAGE_NEGATIVE_NUMBER = "Quantity cannot be a negative number.";

    @Override
    public Integer parse(String quantityString) throws InvalidArgFormatException {
        checkArgNotBlank(quantityString, "Quantity cannot be blank.");
        try {
            final int quantity = Integer.parseInt(quantityString);
            if (quantity < 0) {
                throw new InvalidArgFormatException(MESSAGE_NEGATIVE_NUMBER);
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new InvalidArgFormatException(MESSAGE_INVALID_NUMBER);
        }
    }
}
