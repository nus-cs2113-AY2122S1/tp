package expiryeliminator.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expiryeliminator.parser.exception.InvalidPrefixException;
import expiryeliminator.parser.exception.MissingPrefixException;
import expiryeliminator.parser.exception.MultipleArgsException;

public class ArgParser {
    private final HashMap<String, ArrayList<String>> prefixesToArgs = new HashMap<>();

    /**
     * Used for separation of each prefix and argument.
     * E.g. this matches "/a aaaaa aaaa"
     */
    private static final Pattern ARGS_FORMAT = Pattern.compile("\\w+/([^/\\s]+( +|$))+");

    private final Prefix[] prefixList;

    public ArgParser(Prefix... prefixList) {
        this.prefixList = prefixList;

        for (Prefix argPrefix : prefixList) {
            prefixesToArgs.put(argPrefix.getPrefix(), new ArrayList<>());
        }
    }

    public void parse(String args) throws InvalidPrefixException, MissingPrefixException {
        args = args == null ? "" : args.trim();

        final Matcher matcher = ARGS_FORMAT.matcher(args);

        findAndPopulateArgs(matcher);

        checkAllArgsPresent();
    }

    private void findAndPopulateArgs(Matcher matcher) throws InvalidPrefixException {
        while (matcher.find()) {
            final String match = matcher.group();
            final String[] prefixAndArg = match.split("/");
            final String prefix = prefixAndArg[0];
            final String arg = prefixAndArg[1].trim();
            try {
                prefixesToArgs.get(prefix).add(arg);
            } catch (NullPointerException error) {
                throw new InvalidPrefixException(prefix);
            }
        }
    }

    private void checkAllArgsPresent() throws MissingPrefixException {
        for (Prefix prefix : prefixList) {
            final ArrayList<String> argList = prefixesToArgs.get(prefix.getPrefix());
            if (argList.size() == 0) {
                throw new MissingPrefixException(prefix);
            }
        }
    }

    public String getSingleArg(Prefix prefix) throws MissingPrefixException, MultipleArgsException {
        final ArrayList<String> argList = prefixesToArgs.get(prefix.getPrefix());
        if (argList == null) {
            throw new MissingPrefixException(prefix);
        }
        if (argList.size() > 1) {
            throw new MultipleArgsException();
        }

        return argList.get(0);
    }

    public ArrayList<String> getArgList(Prefix prefix) throws MissingPrefixException {
        final ArrayList<String> argList = prefixesToArgs.get(prefix.getPrefix());
        if (argList == null) {
            throw new MissingPrefixException(prefix);
        }
        return argList;
    }
}
