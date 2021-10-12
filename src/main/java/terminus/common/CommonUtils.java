package terminus.common;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import terminus.exception.InvalidArgumentException;

public class CommonUtils {

    /**
     * Method to get arguments.
     *
     * @param arg String containing the arguments
     * @return An array list containing the separated arguments
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
     * Checks if an array list is empty.
     *
     * @param argArray The array list to be checked
     * @return True if array list is empty, false otherwise
     */
    public static boolean isArrayEmpty(ArrayList<String> argArray) {
        assert argArray != null;
        if (argArray.isEmpty()) {
            return true;
        }
        return argArray.stream().anyMatch(x -> x == null || x.isBlank());
    }

    /**
     * Converts string to a LocalTime object.
     *
     * @param startTime The string to be converted to a LocalTime object
     * @return A LocalTime object of the converted string
     * @throws InvalidArgumentException Exception for when string does not follow the proper time format
     */
    public static LocalTime convertToLocalTime(String startTime) throws InvalidArgumentException {
        assert startTime != null;
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(CommonFormat.LOCAL_TIME_FORMAT);
            return LocalTime.parse(startTime, format);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(
                    String.format(Messages.ERROR_MESSAGE_INVALID_TIME_FORMAT, CommonFormat.LOCAL_TIME_FORMAT));
        }

    }

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
