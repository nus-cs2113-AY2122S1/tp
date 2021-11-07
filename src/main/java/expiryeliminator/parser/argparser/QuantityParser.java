package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses quantity.
 */
public class QuantityParser extends MultipleArgParser<Integer> {
    public static final String MESSAGE_BLANK_QUANTITY = "Quantity cannot be blank";
    private static final String MESSAGE_INVALID_NUMBER = "Quantity must be a valid number.";
    private static final String MESSAGE_NEGATIVE_NUMBER = "Quantity cannot be a negative number.";

    /**
     * Parses quantity.
     *
     * @param quantityString String to be parsed as a quantity.
     * @return Parsed quantity.
     * @throws InvalidArgFormatException If the string is blank, is not a valid number, or is negative.
     */
    @Override
    public Integer parse(String quantityString) throws InvalidArgFormatException {
        checkArgNotBlank(quantityString, MESSAGE_BLANK_QUANTITY);
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
