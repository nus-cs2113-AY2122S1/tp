package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.commons.core.Message;
import seedu.duke.commons.core.Priority;
import seedu.duke.commons.core.exceptions.DayOfTheWeekException;
import seedu.duke.commons.core.exceptions.PriorityException;
import seedu.duke.logic.parser.exceptions.ParseException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//@@author richwill28
public class ParserUtil {
    public static CommandType parseCommandType(String userResponse) {
        String[] params = userResponse.split(" ", 2);
        return CommandType.of(params[0]);
    }

    public static int parseToZeroIndex(int index) {
        return index - 1;
    }

    public static String removeFirstParam(String userResponse, String firstParam) {
        return userResponse.replaceFirst(firstParam, "").strip();
    }

    public static boolean isVerbose(String userResponse) {
        return userResponse.equalsIgnoreCase("verbose")
                || userResponse.equalsIgnoreCase("-v");
    }

    //@@author Roycius

    /**
     * Checks if the number of items in an array of Strings is within a certain range.
     *
     * @param params the array of Strings
     * @param min the lower bound of the range
     * @param max the upper bound of the range
     * @throws ParseException when the number of items is out of the given range
     */
    public static void checkParamsLength(String[] params, int min, int max) throws ParseException {
        if (params.length < min || params.length > max) {
            throw new ParseException(Message.ERROR_INVALID_NUMBER_OF_PARAMS);
        }
    }

    public static String parseTitle(String param) throws ParseException {
        String title = param.strip();
        if (title.isBlank()) {
            throw new ParseException(Message.ERROR_INVALID_TITLE);
        }
        return title;
    }

    public static String parseTime(String param) throws ParseException {
        try {
            return LocalTime.parse(param.strip()).format(DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new ParseException(Message.ERROR_INVALID_TIME_FORMAT);
        }
    }

    public static String parseDayOfTheWeek(String param) throws ParseException {
        try {
            return DayOfTheWeek.toProper(param.strip());
        } catch (DayOfTheWeekException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public static String parsePriority(String param) throws ParseException {
        try {
            return Priority.toProper(param.strip());
        } catch (PriorityException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Parses a string into a HashMap with flags as the key and the corresponding
     * parameters as the value. Flags need to have at least one space behind them
     * to be recognised from string to be parsed.
     * Example flag: "-f"
     *
     * @param userResponse the string to be parsed for flags
     * @param flags all the flags to check for
     * @return the HashMap
     * @throws ParseException when there is a duplicate flag in the userResponse
     */
    public static HashMap<String, String> getFlagMap(String userResponse, String... flags) throws ParseException {
        //make hashmap of flags to index position of flags
        HashMap<String, Integer> flagToPosMap = new HashMap<>();
        for (String flag : flags) { //for each possible flag
            int index = userResponse.indexOf(flag + " ");
            if (index > -1) { //if flag exists
                checkDuplicateFlags(flag + " ", userResponse);
                flagToPosMap.put(flag, index);
            }
        }

        //make hashmap of flags to parameter of flags
        ArrayList<Integer> posList = new ArrayList<>(flagToPosMap.values()); //list of positions of all flags
        Collections.sort(posList);
        HashMap<String, String> flagToParamMap = new HashMap<>();
        for (String flag : flagToPosMap.keySet()) {
            int pos = flagToPosMap.get(flag);
            int indexInPosList = posList.indexOf(pos);
            if (indexInPosList < posList.size() - 1) { //if not the last element of posList array
                int nextPos = posList.get(indexInPosList + 1);
                flagToParamMap.put(flag, userResponse.substring(pos + 3, nextPos).strip());
            } else {
                flagToParamMap.put(flag, userResponse.substring(pos + 3).strip());
            }
        }
        return flagToParamMap;
    }

    public static void checkDuplicateFlags(String flag, String userResponse) throws ParseException {
        int firstIndex = userResponse.indexOf(flag);
        if (userResponse.substring(firstIndex + 1).contains(flag)) {
            throw new ParseException(Message.ERROR_DUPLICATE_FLAGS);
        }
    }
}
