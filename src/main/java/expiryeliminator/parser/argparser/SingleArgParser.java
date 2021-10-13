package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Represents a parser that parses a single argument.
 */
public abstract class SingleArgParser<T> {
    /**
     * Parses a single argument.
     *
     * @param argString Argument string to be parsed.
     * @return The parsed argument.
     * @throws InvalidArgFormatException If the argument is of an invalid format.
     */
    public abstract T parse(String argString) throws InvalidArgFormatException;

    /**
     * Checks that the argument is not blank.
     *
     * @param argString Argument string to be checked.
     * @param errorMessage Error message to be used for the exception if the argument is blank.
     * @throws InvalidArgFormatException If argument is blank.
     */
    protected void checkArgNotBlank(String argString, String errorMessage) throws InvalidArgFormatException {
        if (argString == null || argString.isBlank()) {
            throw new InvalidArgFormatException(errorMessage);
        }
    }
}
