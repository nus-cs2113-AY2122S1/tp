package seedu.duke.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import seedu.duke.exception.ParseDateFailedException;

public class DateParser {
    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy hh:mm";

    private static String STARTS_WITH_REGEX = "^";
    private static String ENDS_WITH_REGEX = "$";
    private static String WHITESPACE_REGEX = "\\s+";


    private static final String[] ACCEPTED_SEPERATORS
        = {":", "_", "~", ".", "-", "/", "|", "\\", WHITESPACE_REGEX};

    private enum Day {
        YESTERDAY(-1),
        TODAY(0),
        TOMORROW(1);

        private final int daysToAdd;

        Day(int daysToAdd) {
            this.daysToAdd = daysToAdd;
        }

        private String getRegex() {
            return STARTS_WITH_REGEX + super.toString().toLowerCase() + WHITESPACE_REGEX + ENDS_WITH_REGEX;
        }

        private static boolean isDay(String input) {
            for (Day day : Day.values()) {
                if (day.getRegex().matches(input.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }

        private static LocalDateTime getDate(String input) {
            for (Day day : Day.values()) {
                if (day.getRegex().matches(input.toLowerCase())) {
                    return LocalDateTime.now().plusDays(day.daysToAdd);
                }
            }
            return null;
        }

    }

    private enum Prefix {
        LAST(-1),
        THIS(0),
        NEXT(1);

        private final int toAdd;

        Prefix(int toAdd) {
            this.toAdd = toAdd;
        }

        private String getRegex() {
            return STARTS_WITH_REGEX + super.toString().toLowerCase() + WHITESPACE_REGEX;
        }

        private static boolean hasPrefix(String input) {
            for (Prefix prefix : Prefix.values()) {
                if (prefix.getRegex().matches(input.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }

        private static Prefix getPrefix(String input) {
            for (Prefix prefix : Prefix.values()) {
                if (prefix.getRegex().matches(input.toLowerCase())) {
                    return prefix;
                }
            }
            return null;
        }

        private String removePrefixFromString(String input) {
            return input.replaceAll(getRegex(), "");
        }
    }

    private enum Prefix {
        LAST(-1),
        THIS(0),
        NEXT(1);

        private final int toAdd;

        Prefix(int toAdd) {
            this.toAdd = toAdd;
        }

        private String getRegex() {
            return STARTS_WITH_REGEX + super.toString().toLowerCase() + WHITESPACE_REGEX;
        }

        private static boolean hasPrefix(String input) {
            for (Prefix prefix : Prefix.values()) {
                if (prefix.getRegex().matches(input.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }

        private static Prefix getPrefix(String input) {
            for (Prefix prefix : Prefix.values()) {
                if (prefix.getRegex().matches(input.toLowerCase())) {
                    return prefix;
                }
            }
            return null;
        }

        private String removePrefixFromString(String input) {
            return input.replaceAll(getRegex(), "");
        }
    }

    private static final String WEEK = "week";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    private static String[] PERIOD = {WEEK, MONTH, YEAR};

    public static String getDateAsString(LocalDateTime date) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static LocalDateTime getStringAsDate(String date) throws ParseDateFailedException {
        LocalDateTime dateTime = Day.getDate(date);
        if (Day.isDay(date)) {
            return Day.getDate(date);
        } else if (Prefix.hasPrefix(date)) {
            Prefix prefix = Prefix.getPrefix(date);
            date = prefix.removePrefixFromString(date);

        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException dtpe) {
                throw new ParseDateFailedException(getDefaultDateFormat());
            }
        }
    }

    public static String getDefaultDateFormat() {
        return DEFAULT_DATE_FORMAT;
    }
}
