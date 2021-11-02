package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.commons.core.Messages;
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

    public static int parseToOneIndex(int index) {
        return index + 1;
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
            throw new ParseException(Messages.ERROR_INVALID_NUMBER_OF_PARAMS);
        }
    }

    public static String parseTitle(String param) throws ParseException {
        String title = param.strip();
        if (title.isBlank()) {
            throw new ParseException(Messages.ERROR_INVALID_TITLE);
        }
        return title;
    }

    public static String parseTime(String param) throws ParseException {
        String time = param.strip();
        try {
            time = LocalTime.parse(param).format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }
        return time;
    }

    public static String parseDayOfTheWeek(String param) throws ParseException {
        String dayOfTheWeek;
        try {
            dayOfTheWeek = DayOfTheWeek.toProper(param.strip());
        } catch (DayOfTheWeekException e) {
            throw new ParseException(e.getMessage());
        }
        return dayOfTheWeek;
    }

    public static String parsePriority(String param) throws ParseException {
        String priority;
        try {
            priority = Priority.toProper(param.strip());
        } catch (PriorityException e) {
            throw new ParseException(e.getMessage());
        }
        return priority;
    }

    public static HashMap<String, String> getFlagMap(String userResponse, String... flags) throws ParseException {
        //make hashmap of flags to index position of flags
        HashMap<String, Integer> flagToPosMap = new HashMap<String, Integer>();
        ArrayList<Integer> posList = new ArrayList<Integer>(); //list of positions of all flags
        for (String flag : flags) { //for each possible flag
            int index = userResponse.indexOf(flag + " ");
            if (index > -1) { //if flag exists
                checkDuplicateFlags(flag + " ", userResponse);
                flagToPosMap.put(flag, index);
                posList.add(index); //add index of valid flag to list
            }
        }
        //make hashmap of flags to parameter of flags
        Collections.sort(posList);
        HashMap<String, String> flagToParameterMap = new HashMap<String, String>();
        for (String flag : flagToPosMap.keySet()) {
            int pos = flagToPosMap.get(flag);
            int indexInPosList = posList.indexOf(pos);
            if (indexInPosList < posList.size() - 1) { //if not last element in postList array
                int nextPos = posList.get(indexInPosList + 1);
                flagToParameterMap.put(flag, userResponse.substring(pos + 3, nextPos).strip());
            } else {
                flagToParameterMap.put(flag, userResponse.substring(pos + 3).strip());
            }
        }
        return flagToParameterMap;
    }

    public static void checkDuplicateFlags(String flag, String userResponse) throws ParseException {
        int firstIndex = userResponse.indexOf(flag);
        if (userResponse.substring(firstIndex + 1).contains(flag)) {
            throw new ParseException(Messages.ERROR_DUPLICATE_FLAGS);
        }
    }
}
