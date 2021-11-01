package expiryeliminator.parser.argparser;

//@@author JoshHDs

import expiryeliminator.common.Utils;
import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Parses unit.
 */
public class UnitParser extends SingleArgParser<String> {

    private static final String MESSAGE_INVALID_UNIT = "Units must only consist of letters, "
            + "not numbers or other special digits.";

    /**
     * Parses unit.
     *
     * @param unitString String to be parsed as a unit.
     * @return Parsed unit.
     */
    @Override
    public String parse(String unitString) throws InvalidArgFormatException {
        if (unitString != null && !Utils.isAlphabetic(unitString)) {
            throw new InvalidArgFormatException(MESSAGE_INVALID_UNIT);
        }
        return unitString;
    }
}
