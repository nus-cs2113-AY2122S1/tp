package terminus.common;


import java.time.format.DateTimeParseException;
import java.net.URL;
import terminus.exception.InvalidArgumentException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CommonFormat class to manage methods and formats that are used across different packages.
 */
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
     * Returns an ArrayList of String containing elements found based on regex.
     * Matches any strings that is within a pair of double quotes.
     *
     * @param arg String containing the arguments from inputs.
     * @return An array list containing strings that is within a pair of double quotes from arg.
     */
    public static ArrayList<String> findArguments(String arg) {
        assert arg != null;
        ArrayList<String> argsArray = new ArrayList<>();
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(arg);
        while (m.find()) {
            argsArray.add(m.group(1));
        }
        return argsArray;
    }

    /**
     * Checks if any elements in the ArrayList of String is empty.
     *
     * @param argArray The ArrayList to be checked.
     * @return True if array list is empty, false otherwise.
     */
    public static boolean isArrayEmpty(ArrayList<String> argArray) {
        assert argArray != null;
        if (argArray.isEmpty()) {
            return true;
        }
        for (String s : argArray) {
            if (s == null || s.isBlank()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a LocalTime object from a given string.
     *
     * @param startTime The string to be converted to a LocalTime object.
     * @return A LocalTime object of the converted string.
     * @throws InvalidArgumentException when string does not follow the proper time format.
     */
    public static LocalTime convertToLocalTime(String startTime) throws InvalidArgumentException {
        assert startTime != null;
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(LOCAL_TIME_FORMAT);
            return LocalTime.parse(startTime, format);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(
                    String.format(Messages.ERROR_MESSAGE_INVALID_TIME_FORMAT, LOCAL_TIME_FORMAT));
        }

    }

    /**
     * Returns the class name without its packages.
     *
     * @param type Content class type.
     * @param <T> Content object type.
     * @return A string of the class name from the class type without its packages.
     */
    public static <T> String getClassName(T type) {
        assert type != null;
        String result = type.toString();
        String[] string = result.split("\\.");
        if (string.length > 0) {
            result = string[string.length - 1];
        }
        return result;
    }

    public static boolean isValidUrl(String url) throws InvalidArgumentException {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            throw new InvalidArgumentException(
                    String.format(Messages.ERROR_MESSAGE_INVALID_LINK, url));
        }
    }

    public static boolean isValidDay(String day) {
        for (DaysOfWeekEnum dayOfWeek : DaysOfWeekEnum.values()) {
            if (dayOfWeek.name().equalsIgnoreCase(day)) {
                return true;
            }
        }
        return false;
    }
}
