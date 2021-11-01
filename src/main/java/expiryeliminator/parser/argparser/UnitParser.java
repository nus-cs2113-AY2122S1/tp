package expiryeliminator.parser.argparser;

//@@author JoshHDs

import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses unit.
 */
public class UnitParser extends SingleArgParser<String> {

    private static final String MESSAGE_INVALID_UNIT = "Units must only consist of words, not numbers.";

    /**
     * Parses unit.
     *
     * @param unitString String to be parsed as a unit.
     * @return Parsed unit.
     */
    @Override
    public String parse(String unitString) throws InvalidArgFormatException {

        if (unitString != null) {
            for (int i = 0; i < unitString.length() ; i++) {
                char c = unitString.charAt(i);

                if (!Character.isLetter(c)) {
                    throw new InvalidArgFormatException(MESSAGE_INVALID_UNIT);
                }
            }
        }

        return unitString;
    }
}
