package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;

//@@author richwill28
public class ParserUtil {
    public static boolean hasCorrectFlagSequence(String userResponse, String... flags) {
        for (int i = 0; i < flags.length - 1; i++) {
            int leftFlagIndex = userResponse.indexOf(flags[i]);
            int rightFlagIndex = userResponse.indexOf(flags[i + 1]);
            if (leftFlagIndex >= rightFlagIndex) {
                return false;
            }
        }
        return true;
    }

    public static CommandType parseCommandType(String userResponse) {
        String[] params = userResponse.split(" ", 2);
        return CommandType.of(params[0]);
    }

    public static int parseToOneIndex(int index) {
        return index + 1;
    }

    public static int parseToZeroIndex(int index) {
        return index - 1;
    }

    public static String removeFirstParam(String userResponse, String firstParam) {
        return userResponse.replaceFirst(firstParam, "").strip();
    }

    public static boolean isAll(String userResponse) {
        return userResponse.equalsIgnoreCase("all");
    }
}
