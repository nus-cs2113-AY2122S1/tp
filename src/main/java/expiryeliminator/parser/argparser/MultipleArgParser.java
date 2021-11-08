package expiryeliminator.parser.argparser;

import java.util.ArrayList;

import expiryeliminator.parser.exception.InvalidArgFormatException;

/**
 * Represents a parser that parses multiple arguments.
 */
public abstract class MultipleArgParser<T> extends SingleArgParser<T> {
    /**
     * Parses a list of arguments.
     *
     * @param argList List of argument strings to be parsed.
     * @return List of parsed arguments.
     * @throws InvalidArgFormatException If any of the arguments is of an invalid format.
     */
    public ArrayList<T> parse(ArrayList<String> argList) throws InvalidArgFormatException {
        final ArrayList<T> results = new ArrayList<>();
        for (String argString : argList) {
            results.add(parse(argString));
        }
        return results;
    }
}
