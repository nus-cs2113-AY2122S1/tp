package expiryeliminator.parser.argparser;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public abstract class SingleArgParser<T> {
    public abstract T parse(String argString) throws InvalidArgFormatException;

    protected void checkArgNotBlank(String argString, String errorMessage) throws InvalidArgFormatException {
        if (argString == null || argString.isBlank()) {
            throw new InvalidArgFormatException(errorMessage);
        }
    }
}
