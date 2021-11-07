package seedu.duke.parser.schedule;

import seedu.duke.exceptions.calendar.IncorrectNumberOfArgumentsException;
import seedu.duke.exceptions.calendar.InvalidDateException;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.duke.constants.Messages.TODO;
import static seedu.duke.constants.Messages.CALENDAR_COMMAND_SPLIT;
import static seedu.duke.constants.Messages.DELIMITER_DATE;
import static seedu.duke.constants.Messages.MONTH_UPPER_LIMIT;
import static seedu.duke.constants.Messages.INDEX_ZERO;
import static seedu.duke.constants.Messages.MODULE_DIVIDER_NOT_FOUND;
import static seedu.duke.constants.Messages.STARTDATE_DIVIDER_NOT_FOUND;
import static seedu.duke.constants.Messages.ENDDATE_DIVIDER_NOT_FOUND;
import static seedu.duke.constants.Messages.NAME_ABSENT;
import static seedu.duke.constants.Messages.DATE_ABSENT;
import static seedu.duke.constants.Messages.MODULE_ABSENT;
import static seedu.duke.constants.Messages.START_DATE_ABSENT;
import static seedu.duke.constants.Messages.END_DATE_ABSENT;
import static seedu.duke.constants.Messages.DATE_DIVIDER_NOT_FOUND;
import static seedu.duke.constants.Messages.NAME_DIVIDER_NOT_FOUND;
import static seedu.duke.constants.Messages.DATE_DIVIDER;
import static seedu.duke.constants.Messages.NAME_DIVIDER;
import static seedu.duke.constants.Messages.MODULE_DIVIDER;
import static seedu.duke.constants.Messages.START_DATE_DIVIDER;
import static seedu.duke.constants.Messages.END_DATE_DIVIDER;

public class ParserSchedule {

    /**
     * Parsing of calendar command for JUnit.
     *
     * @param input from user.
     * @return year and month.
     */
    public static YearMonth parseCalendarCommandForJunit(String input) {
        // takes substring excluding "calendar" from command
        String extractMonthYear = input.substring(17);
        var arguments = extractMonthYear.split(DELIMITER_DATE);
        int month = Integer.parseInt(arguments[INDEX_ZERO]);
        assert month <= MONTH_UPPER_LIMIT;
        int year = Integer.parseInt(arguments[1]);
        return YearMonth.of(year, month);
    }


    /**
     * Parsing of todo command.
     *
     * @param input from user.
     * @return Checking for whether arguments are present.
     * @throws IncorrectNumberOfArgumentsException to check if correct number of arguments.
     */
    public static ArrayList<String> parseTodoCommand(String input)
            throws IncorrectNumberOfArgumentsException, InvalidDateException {
        return parseTodoArgumentsArray(input);
    }

    private static void checkForDividersAddTaskCommand(String input) throws IncorrectNumberOfArgumentsException {
        if (!input.contains(NAME_DIVIDER)) {
            throw new IncorrectNumberOfArgumentsException(NAME_DIVIDER_NOT_FOUND);
        }
        if (!input.contains(DATE_DIVIDER)) {
            throw new IncorrectNumberOfArgumentsException(DATE_DIVIDER_NOT_FOUND);
        }
    }

    private static void checkForDividersAddLectureCommand(String input) throws IncorrectNumberOfArgumentsException {
        if (!input.contains(MODULE_DIVIDER)) {
            throw new IncorrectNumberOfArgumentsException(MODULE_DIVIDER_NOT_FOUND);
        }
        if (!input.contains(START_DATE_DIVIDER)) {
            throw new IncorrectNumberOfArgumentsException(STARTDATE_DIVIDER_NOT_FOUND);
        }
        if (!input.contains(END_DATE_DIVIDER)) {
            throw new IncorrectNumberOfArgumentsException(ENDDATE_DIVIDER_NOT_FOUND);
        }
    }

    private static void checkEmptyIncorrectArgsAddTaskCommand(String description, String date)
            throws IncorrectNumberOfArgumentsException, InvalidDateException {
        if (description.equals("")) {
            throw new IncorrectNumberOfArgumentsException(NAME_ABSENT);
        }
        if (date.equals("")) {
            throw new IncorrectNumberOfArgumentsException(DATE_ABSENT);
        } else if (date.length() != 10) {
            throw new InvalidDateException();
        }
    }

    private static void checkEmptyIncorrectArgsAddLectureCommand(String name, String fromDate, String toDate)
            throws IncorrectNumberOfArgumentsException, InvalidDateException {
        if (name.equals("")) {
            throw new IncorrectNumberOfArgumentsException(MODULE_ABSENT);
        }
        if (fromDate.equals("")) {
            throw new IncorrectNumberOfArgumentsException(START_DATE_ABSENT);
        } else if (fromDate.length() != 10) {
            throw new InvalidDateException();
        }
        if (toDate.equals("")) {
            throw new IncorrectNumberOfArgumentsException(END_DATE_ABSENT);
        } else if (toDate.length() != 10) {
            throw new InvalidDateException();
        }
    }

    /**
     * Parsing of todo arguments.
     *
     * @param input from user.
     * @return Returning arguments of todo command.
     * @throws IncorrectNumberOfArgumentsException to check if correct number of arguments.
     * @throws InvalidDateException if date given by user is invalid.
     */
    public static ArrayList<String> parseTodoArgumentsArray(String input)
            throws IncorrectNumberOfArgumentsException, InvalidDateException {
        checkForDividersAddTaskCommand(input);
        String todoDetails = input.trim().substring(CALENDAR_COMMAND_SPLIT);
        int nameIndex = todoDetails.indexOf(NAME_DIVIDER);
        String descriptionAndDate = todoDetails.substring(nameIndex).trim();
        int dateIndex = descriptionAndDate.indexOf(DATE_DIVIDER);
        String description = descriptionAndDate.substring(nameIndex + 2, dateIndex).trim();
        String date = descriptionAndDate.substring(dateIndex + 2).trim();
        checkEmptyIncorrectArgsAddTaskCommand(description, date);
        List<String> todoInformation = Arrays.asList(TODO, description, date);
        return new ArrayList<>(todoInformation);
    }

    /**
     * Parsing of calendar command.
     *
     * @param input from user.
     * @return month and year arguments.
     */
    public static String[] parseCalendarCommand(String input) {
        String extractMonthYear = input.substring(17).trim();
        return extractMonthYear.split(DELIMITER_DATE);
    }

    /**
     * Parsing of lecture command.
     *
     * @param input The input from the user.
     * @return the parsed lecture command arguments if the command is correct.
     * @throws IncorrectNumberOfArgumentsException if the user command is incorrect.
     * @throws InvalidDateException if date given by user is invalid.
     */
    public static ArrayList<String> parseLectureCommand(String input)
            throws IncorrectNumberOfArgumentsException, InvalidDateException {
        return parseLectureArgumentsArray(input);
    }

    /**
     * Parsing of lecture arguments.
     *
     * @param input Input from user.
     * @return the name of lecture, start date and end date.
     * @throws IncorrectNumberOfArgumentsException if the user command is incorrect.
     * @throws InvalidDateException if date given by user is invalid.
     */
    public static ArrayList<String> parseLectureArgumentsArray(String input)
            throws IncorrectNumberOfArgumentsException, InvalidDateException {
        checkForDividersAddLectureCommand(input);
        String lectureDetails = input.trim().substring(17);
        int moduleIndexFormer = lectureDetails.indexOf(MODULE_DIVIDER);
        String nameAndDate = lectureDetails.substring(moduleIndexFormer).trim();
        int moduleIndexLatter = nameAndDate.indexOf(MODULE_DIVIDER);
        int startDateIndexFormer = nameAndDate.indexOf(START_DATE_DIVIDER);
        String name = nameAndDate.substring(moduleIndexLatter + 2, startDateIndexFormer).trim();
        String dayAndLimits = nameAndDate.substring(startDateIndexFormer).trim();
        int startDateIndexLatter = dayAndLimits.indexOf(START_DATE_DIVIDER);
        int endDateIndex = dayAndLimits.indexOf(END_DATE_DIVIDER);
        String fromDate = dayAndLimits.substring(startDateIndexLatter + 2,  endDateIndex).trim();
        String toDate = dayAndLimits.substring(endDateIndex + 2).trim();
        checkEmptyIncorrectArgsAddLectureCommand(name, fromDate, toDate);
        List<String> lectureInformation = Arrays.asList(name, fromDate, toDate);
        return new ArrayList<>(lectureInformation);
    }

}
