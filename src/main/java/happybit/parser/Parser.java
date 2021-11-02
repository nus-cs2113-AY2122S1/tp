package happybit.parser;

import happybit.exception.HaBitParserException;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    protected static final String DELIMITER = "\u0001";
    protected static final String LABEL_SYNTAX = "[a-zA-Z]/";
    protected static final String DATE_FORMAT = "ddMMyyyy";

    protected static final String FLAG_GOAL_INDEX = "g/";
    protected static final String FLAG_NAME = "n/";
    protected static final String FLAG_GOAL_TYPE = "t/";
    protected static final String FLAG_INTERVAL = "i/";
    protected static final String FLAG_START_DATE = "s/";
    protected static final String FLAG_END_DATE = "e/";
    protected static final String FLAG_HABIT_INDEX = "h/";

    protected static final String ERROR_NO_PARAMS = "Command cannot be called without parameters. "
            + "Enter the help command to view command formats";

    /**
     * Splits the input into the various parameters.
     *
     * @param input User input.
     * @return String array containing the parameters.
     */
    protected static String[] splitInput(String input) {
        Function<MatchResult, String> replace = x -> DELIMITER + x.group();
        Pattern pattern = Pattern.compile(LABEL_SYNTAX);
        Matcher matcher = pattern.matcher(input);
        String processedInput = matcher.replaceAll(replace);
        String[] parameters = processedInput.split(DELIMITER);
        return trimParameters(parameters);
    }

    /**
     * Removes leading and trailing whitespaces if any.
     *
     * @param parameters String array of command parameters.
     * @return String array of command parameters that have been trimmed of leading/trailing whitespaces.
     */
    private static String[] trimParameters(String[] parameters) {
        for (int i = 1; i < parameters.length; i++) {
            parameters[i] = parameters[i].substring(0, 2) + parameters[i].substring(2).trim();
        }
        return parameters;
    }

    /**
     * Finds the parameters corresponding to the given label and returns it.
     *
     * @param parameters String array of command parameters.
     * @param label      Label of a parameter.
     * @return Parameter if it exists, null otherwise.
     */
    protected static String getParameter(String[] parameters, String label) {
        for (String parameter : parameters) {
            if (parameter.contains(label)) {
                return parameter;
            }
        }
        return null;
    }

    /**
     * Checks if the input is null.
     *
     * @param input String of the user input.
     * @throws HaBitParserException If the user input is null (blank).
     */
    protected static void checkNoDescription(String input) throws HaBitParserException {
        if (input == null) {
            throw new HaBitParserException(ERROR_NO_PARAMS);
        }
    }

}
