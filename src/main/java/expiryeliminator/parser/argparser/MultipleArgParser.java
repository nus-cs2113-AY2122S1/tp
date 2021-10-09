package expiryeliminator.parser.argparser;

import java.util.ArrayList;

import expiryeliminator.parser.exception.InvalidArgFormatException;

public abstract class MultipleArgParser<T> extends SingleArgParser<T> {
    public ArrayList<T> parse(ArrayList<String> argList) throws InvalidArgFormatException {
        final ArrayList<T> results = new ArrayList<>();
        for (String argString : argList) {
            results.add(parse(argString));
        }
        return results;
    }
}
