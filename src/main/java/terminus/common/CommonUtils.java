package terminus.common;

import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import terminus.exception.InvalidArgumentException;

/**
 * CommonUtils class to manage methods that are used across different packages.
 */
public class CommonUtils {

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
    public static boolean hasEmptyString(ArrayList<String> argArray) {
        assert argArray != null;
        if (argArray.isEmpty()) {
            return true;
        }
        return argArray.stream().anyMatch(x -> x == null || x.isBlank());
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
            DateTimeFormatter format = DateTimeFormatter.ofPattern(CommonFormat.LOCAL_TIME_FORMAT);
            return LocalTime.parse(startTime, format);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(
                    String.format(Messages.ERROR_MESSAGE_INVALID_TIME_FORMAT, CommonFormat.LOCAL_TIME_FORMAT));
        }

    }

    /**
     * Returns the class name without its packages.
     *
     * @param type Content class type.
     * @param <T>  Content object type.
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

    /**
     * Checks if the given string is a valid url.
     *
     * @param url The string to be checked.
     * @return True if url is valid, false otherwise.
     * @throws InvalidArgumentException when given string is not a valid url.
     */
    public static boolean isValidUrl(String url) throws InvalidArgumentException {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the given string is a valid DaysOfWeekEnum type.
     *
     * @param day The string to be checked.
     * @return True if day is a valid DaysOfWeekEnum type, false otherwise.
     */
    public static boolean isValidDay(String day) {
        for (DaysOfWeekEnum dayOfWeek : DaysOfWeekEnum.values()) {
            if (dayOfWeek.name().equalsIgnoreCase(day)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given integer is a valid duration.
     *
     * @param duration The integer to be checked
     * @return True if the integer between 0 and 24 inclusive, false otherwise
     */
    public static boolean isValidDuration(int duration) {
        if (duration < 0 || duration > 24) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the given LocalTime and integer has an overflow.
     *
     * @param startTime The startTime attribute of the Link object
     * @param duration  The duration attribute of the Link object
     * @return True if there is no overflow in time, false otherwise
     */
    public static boolean hasDurationOverflow(LocalTime startTime, int duration) {
        LocalTime endTime = startTime.plusHours(duration);
        LocalTime midnight = LocalTime.of(00, 00);
        if (!endTime.equals(midnight) && startTime.getHour() > endTime.getHour()) {
            return true;
        } else if (!startTime.equals(midnight) && duration == 24) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the given String is null or empty.
     *
     * @param string The string to be checked
     * @return True if String is null or empty, false otherwise
     */
    public static boolean isStringNullOrEmpty(String string) {
        return string == null || string.isBlank();
    }

    /**
     * Checks if the given name is a valid file name.
     *
     * @param name The string to be checked.
     * @return True if name is a valid file name, false otherwise.
     */
    public static boolean isValidFileName(String name) {
        if (name == null || name.isBlank() || name.length() > CommonFormat.MAX_FILENAME_LENGTH) {
            return false;
        }
        boolean isOnlyAscii = name.chars()
                .allMatch(c -> CommonFormat.STARTING_ASCII <= c && c <= CommonFormat.ENDING_ASCII);
        boolean hasIllegalChar = name.chars().anyMatch(x -> CommonFormat.ILLEGAL_CHARACTERS.contains((char) x));
        if (!isOnlyAscii || hasIllegalChar) {
            return false;
        }
        try {
            Paths.get(name.trim());
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }

    /**
     * Returns the filename without its file extension of a given full filename.
     *
     * @param filename The filename string to be extracted.
     * @return A string of the file name without its file extension.
     */
    public static String getFileNameOnly(String filename) {
        assert filename != null;
        String[] string = filename.split("\\.");
        if (string != null && string.length == 2) {
            return string[0];
        }
        return null;
    }

    /**
     * Function to get today's day of the week.
     *
     * @return A string indicating today's day
     */
    public static String getCurrentDay() {
        String currentDay = LocalDate.now().getDayOfWeek().toString();
        return currentDay;
    }

    /**
     * Returns a boolean if the index give is valid.
     *
     * @param index The index to check
     * @param listOfModule The full list of modules
     * @return True if the index is valid or else it is false
     */
    public static boolean isValidIndex(int index, String[] listOfModule) {
        return listOfModule.length >= index && index > 0;
    }
}
