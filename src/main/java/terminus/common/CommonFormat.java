package terminus.common;

import java.util.Arrays;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidTimeFormatException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonFormat {

    public static final String COMMAND_NOTE = "note";
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_VIEW = "view";
    public static final String COMMAND_BACK = "back";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_SCHEDULE = "schedule";

    public static final String LOCAL_TIME_FORMAT = "HH:mm";

    public static final String COMMAND_DELETE_FORMAT = COMMAND_DELETE + " <item number>";
    public static final String COMMAND_VIEW_FORMAT = COMMAND_VIEW + " {item number}";
    public static final String COMMAND_ADD_SCHEDULE_FORMAT = COMMAND_ADD + " \"<link description>\" "
            + "\"<day>\" \"<start_time " + LOCAL_TIME_FORMAT + ">\" \"<zoom_link>\"";
    public static final String COMMAND_ADD_NOTE_FORMAT = COMMAND_ADD + " \"<note name>\" \"<note content>\"";

    /**
     * Method to get arguments.
     *
     * @param arg String containing the arguments
     * @return An array list containing the separated arguments
     */
    public static ArrayList<String> findArguments(String arg) {
        ArrayList<String> argsArray = new ArrayList<>();
        if (arg == null) {
            assert false;
        }
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(arg);
        while (m.find()) {
            argsArray.add(m.group(1));
        }
        return argsArray;
    }

    /**
     * Checks if an array list is empty.
     *
     * @param argArray The array list to be checked
     * @return True if array list is empty, false otherwise
     */
    public static boolean isArrayEmpty(ArrayList<String> argArray) {
        if (argArray == null) {
            assert false;
        }
        if (argArray.isEmpty()) {
            return true;
        }
        for (String s : argArray) {
            if (s == null) {
                assert false;
            }
            if (s.isBlank()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts string to a LocalTime object.
     *
     * @param startTime The string to be converted to a LocalTime object
     * @return A LocalTime object of the converted string
     * @throws InvalidTimeFormatException Exception for when string does not follow the proper time format
     */
    public static LocalTime convertToLocalTime(String startTime) throws InvalidTimeFormatException {
        if (startTime.length() != 5 || startTime.indexOf(":") != 2) {
            throw new InvalidTimeFormatException(
                    String.format(Messages.ERROR_MESSAGE_INVALID_TIME_FORMAT, LOCAL_TIME_FORMAT));
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern(LOCAL_TIME_FORMAT);
        return LocalTime.parse(startTime, format);
    }

    public static <T> String getClassName(T type) {
        String result = type.toString();
        String[] string = result.split("\\.");
        if (string.length > 0) {
            result = string[string.length - 1];
        }
        return result;
    }
}
