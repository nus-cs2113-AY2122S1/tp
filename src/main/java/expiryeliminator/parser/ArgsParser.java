package expiryeliminator.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expiryeliminator.common.LogsCenter;
import expiryeliminator.parser.exception.InvalidPrefixException;
import expiryeliminator.parser.exception.MissingPrefixException;
import expiryeliminator.parser.exception.MultipleArgsException;
import expiryeliminator.parser.prefix.MultipleArgPrefix;
import expiryeliminator.parser.prefix.OptionalArgPrefix;
import expiryeliminator.parser.prefix.Prefix;
import expiryeliminator.parser.prefix.SingleArgPrefix;

/**
 * Parses arguments based on a specified format and validates them.
 */
class ArgsParser {
    /**
     * Maps each prefix to a list of argument strings that correspond to that prefix.
     */
    private final HashMap<String, ArrayList<String>> prefixesToArgs = new HashMap<>();

    /**
     * Used for separation of each prefix and argument.
     * E.g. this matches "a/aaaaa aaaa".
     */
    private static final Pattern ARGS_FORMAT = Pattern.compile("\\w+/([^/\\s]*( +|$))+");

    private final ArrayList<Prefix> prefixList;
    private static final Logger logger = LogsCenter.getLogger(ArgsParser.class);

    /**
     * Initialises argument parser and stores prefix list.
     *
     * @param prefixList List of prefixes that should be parsed.
     */
    ArgsParser(Prefix... prefixList) {
        assert prefixList.length > 0;
        this.prefixList = new ArrayList<>(Arrays.asList(prefixList));

        for (Prefix prefix : prefixList) {
            assert prefix != null : "Prefix cannot be null";
            prefixesToArgs.put(prefix.getPrefix(), new ArrayList<>());
        }
    }

    /**
     * Parses arguments based on the specified prefixes.
     *
     * @param args Argument string to be parsed.
     * @throws InvalidPrefixException If there is an invalid prefix found in the args that does not correspond
     *         to any of the specified prefixes.
     * @throws MissingPrefixException If there is a missing prefix that could not be found in the args.
     * @throws MultipleArgsException If there is a prefix that appears more than once in the args when it should
     *         have only appeared once.
     */
    void parse(String args) throws InvalidPrefixException, MissingPrefixException, MultipleArgsException {
        args = args == null ? "" : args.trim();

        final Matcher matcher = ARGS_FORMAT.matcher(args);

        findAndPopulateArgs(matcher);

        checkAllArgsPresent();
    }

    /**
     * Extracts arguments from the matcher and populates {@link #prefixesToArgs}.
     *
     * @param matcher Matcher based on the {@link #ARGS_FORMAT} pattern and matching on the argument string.
     * @throws InvalidPrefixException If there is an invalid prefix found in the args that does not correspond
     *         to any of the specified prefixes.
     */
    private void findAndPopulateArgs(Matcher matcher) throws InvalidPrefixException {
        while (matcher.find()) {
            final String match = matcher.group().trim();
            final String[] prefixAndArg = match.split("/");
            logger.log(Level.FINEST, "Parsing prefixAndArg: '" + Arrays.toString(prefixAndArg) + "'");
            final String prefix = prefixAndArg[0];
            String arg = null;
            if (prefixAndArg.length == 2) {
                arg = prefixAndArg[1];
                // Throw error if first character is a space as that is not allowed.
                if (Character.isSpaceChar(arg.charAt(0))) {
                    throw new InvalidPrefixException(prefix);
                }
                arg = arg.trim();
            }
            logger.log(Level.FINEST, "Parsed prefix: '" + prefix + "', parsed arg:'" + arg + "'");
            try {
                prefixesToArgs.get(prefix).add(arg);
            } catch (NullPointerException error) {
                throw new InvalidPrefixException(prefix);
            }
        }
    }

    /**
     * Checks that all required arguments are present.
     *
     * @throws MissingPrefixException If there is a missing prefix that could not be found in the args.
     * @throws MultipleArgsException If there is a prefix that appears more than once in the args when it should
     *         have only appeared once.
     */
    private void checkAllArgsPresent() throws MissingPrefixException, MultipleArgsException {
        for (Prefix prefix : prefixList) {
            final ArrayList<String> argList = prefixesToArgs.get(prefix.getPrefix());
            // Optional args do not need to be present.
            if (!(prefix instanceof OptionalArgPrefix) && argList.size() == 0) {
                throw new MissingPrefixException(prefix);
            }
            if (prefix instanceof SingleArgPrefix && argList.size() > 1) {
                throw new MultipleArgsException();
            }
        }
    }

    /**
     * Returns the arg that corresponds to the given prefix.
     *
     * @param prefix Single arg prefix.
     * @return Arg that corresponds to the given prefix, or null if there is none (only for optional args).
     */
    String getSingleArg(SingleArgPrefix prefix) {
        assert prefix != null && prefixList.contains(prefix)
                : "Prefix cannot be null and must be present in the arg parser's prefix list";
        final ArrayList<String> argList = prefixesToArgs.get(prefix.getPrefix());
        assert argList != null
                : "Arg list should not be null because we should have already initialised with an empty array list";
        if (prefix instanceof OptionalArgPrefix) {
            if (argList.size() == 0) {
                return null;
            }
        }
        assert argList.size() == 1 : "There should be one arg because we should have eliminated all other cases";
        return argList.get(0);
    }

    /**
     * Returns the list of arg that corresponds to the given prefix.
     *
     * @param prefix Multiple arg prefix.
     * @return List of args that correspond to the given prefix.
     */
    ArrayList<String> getArgList(MultipleArgPrefix prefix) {
        assert prefix != null && prefixList.contains(prefix)
                : "Prefix cannot be null and must be present in the arg parser's prefix list";
        final ArrayList<String> argList = prefixesToArgs.get(prefix.getPrefix());
        assert argList != null
                : "Arg list should not be null because we should have already initialised with an empty array list";
        return argList;
    }
}
