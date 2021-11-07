package seedu.duke.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import seedu.duke.schedule.lecture.Lecture;
import seedu.duke.schedule.lecture.LectureList;
import seedu.duke.schedule.task.Task;
import seedu.duke.schedule.task.TaskList;
import seedu.duke.ui.Ui;

import static seedu.duke.constants.Messages.DELIMITER_DATE;
import static seedu.duke.constants.Messages.TOTAL_SIZE;
import static seedu.duke.constants.Messages.DAYS_IN_MONTH;
import static seedu.duke.constants.Messages.INDEX_ZERO;
import static seedu.duke.constants.Messages.INDEX_ONE;
import static seedu.duke.constants.Messages.NUMBER_OF_DAYS_IN_WEEK;
import static seedu.duke.constants.Messages.LEAVE_EMPTY_IN_DISPLAY;
import static seedu.duke.constants.Messages.EMPTY_SPACE;
import static seedu.duke.constants.Messages.TASK_FORMATTER;
import static seedu.duke.constants.Messages.SEPARATOR_DISPLAY;
import static seedu.duke.constants.Messages.CALENDER_DATE_FORMATTER;
import static seedu.duke.constants.Messages.LECTURE_TASK_SPLIT;

//@@author swatimahadevan

/**
 * Represents the logic and UI behind calendar with tasks display.
 */
public class Schedule {

    /**
     * Arranges the tasks from storage and sends
     * each of them for adding to a particular calendar day.
     *
     * @param taskList The list of tasks from storage.
     * @param calendarTasks The ArrayList of all calendar days and tasks.
     * @param month The month input by user.
     * @param year The year input by user.
     */
    public static void arrangeTaskList(TaskList taskList,
                                     ArrayList<ArrayList<String>> calendarTasks, int month, int year) {
        for (Task task : taskList.getTaskList()) {
            String description = task.getDescription();
            String[] dateArguments = task.getDate().split(DELIMITER_DATE);
            addTaskToCalendarDay(dateArguments, description, month, year, calendarTasks);
        }
    }

    /**
     * Arranges the lectures from storage and sends
     * each of them for adding to a particular calendar day.
     *
     * @param lectureList The list of lecture from storage.
     * @param calendarLectures The ArrayList of all calendar days and lectures.
     * @param month The month input by user.
     * @param year The year input by user.
     */
    public static void arrangeLectureList(LectureList lectureList,
                                        ArrayList<ArrayList<String>> calendarLectures, int month, int year) {
        for (Lecture lecture : lectureList.getLectureList()) {
            String moduleName = lecture.getModuleName();
            String[] dateFromArguments = lecture.getDateFrom().split(DELIMITER_DATE);
            String[] dateToArguments = lecture.getDateTo().split(DELIMITER_DATE);
            addLectureToCalendarDay(moduleName, dateFromArguments, dateToArguments, month, year, calendarLectures);
        }
    }

    /**
     * Initializes all the days of the calendar with empty ArrayList
     * to be filled with tasks (if present).
     *
     * @param calendarTasks The ArrayList of all calendar days and tasks.
     */
    public static void intializeCalendarDayTasksList(ArrayList<ArrayList<String>> calendarTasks) {
        while (calendarTasks.size() != TOTAL_SIZE) {
            calendarTasks.add(new ArrayList<>());
        }
    }

    /**
     * Initializes all the days of the calendar with empty ArrayList
     * to be filled with lectures (if present).
     *
     * @param calendarLectures The ArrayList of all calendar days and lectures.
     */
    public static void intializeCalendarDayLectureList(ArrayList<ArrayList<String>> calendarLectures) {
        while (calendarLectures.size() != TOTAL_SIZE) {
            calendarLectures.add(new ArrayList<>());
        }
    }

    private static void addTaskToCalendarDay(String[] dateArguments,
                                             String description, int month,
                                             int year, ArrayList<ArrayList<String>> calendarTasks) {
        if (month == Integer.parseInt(dateArguments[1])
                && year == Integer.parseInt(dateArguments[2])) {
            int date = Integer.parseInt(dateArguments[0]);
            calendarTasks.get(date).add(description);
        }
    }

    private static void addLectureToCalendarDay(String moduleName,
        String[] dateFromArguments, String[] dateToArguments,
        int month, int year, ArrayList<ArrayList<String>> calendarLectures) {
        int paramStartDate = Integer.parseInt(dateFromArguments[0]);
        int paramEndDate = Integer.parseInt(dateToArguments[0]);
        int paramStartMonth = Integer.parseInt(dateFromArguments[1]);
        int paramEndMonth = Integer.parseInt(dateToArguments[1]);
        int paramStartYear = Integer.parseInt(dateFromArguments[2]);
        int paramEndYear = Integer.parseInt(dateToArguments[2]);
        LocalDate localDate = LocalDate.of(paramStartYear, paramStartMonth, paramStartDate);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if ((month >= paramStartMonth && (month <= paramEndMonth))
                && (year >= paramStartYear) && (year <= paramEndYear)) {
            YearMonth yearMonthObject = YearMonth.of(year, month);
            int daysInMonth = yearMonthObject.lengthOfMonth();
            for (int i = 1; i <= daysInMonth; i++) {
                if (LocalDate.of(year, month, i).getDayOfWeek() == dayOfWeek
                        && paramStartMonth == paramEndMonth && paramStartDate <= i && i <= paramEndDate) {
                    calendarLectures.get(i).add(moduleName);
                } else if (LocalDate.of(year, month, i).getDayOfWeek() == dayOfWeek
                        && month != paramStartMonth && month != Integer.parseInt(dateToArguments[1])) {
                    calendarLectures.get(i).add(moduleName);
                } else if (LocalDate.of(year, month, i).getDayOfWeek() == dayOfWeek
                        && month == paramStartMonth && paramStartMonth != paramEndMonth && paramStartDate <= i) {
                    calendarLectures.get(i).add(moduleName);
                } else if (LocalDate.of(year, month, i).getDayOfWeek() == dayOfWeek
                        && month == paramEndMonth && paramStartMonth != paramEndMonth && i <= paramEndDate) {
                    calendarLectures.get(i).add(moduleName);
                }
            }
        }
    }

    /**
     * Prints out the calendar after the calendar display command is used.
     *
     * @param inputYearMonth YearMonth object that is parsed from the year and month given by the user.
     * @param calendarTasks  The calendar
     */
    public static void displayCalendar(YearMonth inputYearMonth,
        ArrayList<ArrayList<String>> calendarTasks, ArrayList<ArrayList<String>> calendarLectures) {
        ArrayList<String> calendar = new ArrayList<>();
        addDatesForDaysInMonth(inputYearMonth, calendar);
        addNoDatesInBeginning(inputYearMonth, calendar);
        addNoDatesInEnd(calendar);

        // Print out the entire display in week rows
        int totalWeeks = (int) Math.ceil((double)calendar.size() / NUMBER_OF_DAYS_IN_WEEK);
        int currentWeek = 0;
        Ui.printCalendarLine();
        printEntireCalendar(totalWeeks, currentWeek, calendar, calendarTasks, calendarLectures);
    }

    private static void printEntireCalendar(int totalWeeks, int currentWeek,
        ArrayList<String> calendar,
        ArrayList<ArrayList<String>> calendarTasks, ArrayList<ArrayList<String>> calendarLectures) {
        AtomicInteger j = new AtomicInteger();
        while (currentWeek < totalWeeks) {
            int indexOfDay = currentWeek * NUMBER_OF_DAYS_IN_WEEK;
            Ui.printDayDemarcation();
            printCalendarDates(calendar, indexOfDay);
            System.out.println();
            printTaskLectureForWeek(calendarTasks, calendarLectures, calendar, indexOfDay);
            Ui.printCalendarLine();
            currentWeek++;
        }
    }

    //Add all dates that are available in the month from 01-value of last day of month
    private static void addDatesForDaysInMonth(YearMonth inputYearMonth, ArrayList<String> calendar) {
        String[] daysInMonth = DAYS_IN_MONTH;
        calendar.addAll(Arrays.asList(daysInMonth).subList(INDEX_ZERO, inputYearMonth.lengthOfMonth()));
    }

    // Add in spaces at the beginning for display upto day 1
    private static void addNoDatesInBeginning(YearMonth inputYearMonth, ArrayList<String> calendar) {
        DayOfWeek dayOneOfMonth = inputYearMonth.atDay(INDEX_ONE).getDayOfWeek();
        if (dayOneOfMonth != DayOfWeek.SUNDAY) {
            IntStream.range(INDEX_ZERO, dayOneOfMonth.getValue()).forEachOrdered(i ->
                    calendar.add(INDEX_ZERO, LEAVE_EMPTY_IN_DISPLAY));
        }
    }

    //Add in spaces at the end after the last day to fill in the week
    private static void addNoDatesInEnd(ArrayList<String> calendar) {
        while (calendar.size() % NUMBER_OF_DAYS_IN_WEEK != INDEX_ZERO) {
            calendar.add(LEAVE_EMPTY_IN_DISPLAY);
        }
    }

    //Print out the top rows with dates from 01-value of last day of month
    private static void printCalendarDates(ArrayList<String> calendar, int indexOfDay) {
        for (int dayOfWeek = INDEX_ZERO; dayOfWeek < NUMBER_OF_DAYS_IN_WEEK; dayOfWeek++) {
            String s = EMPTY_SPACE + calendar.get(indexOfDay + dayOfWeek) + CALENDER_DATE_FORMATTER;
            System.out.print(s);
        }
    }

    /**
     * Prints tasks followed by lectures for a calendar week.
     *
     * @param calendarTasks ArrayList of tasks.
     * @param calendarLectures ArrayList of lectures.
     * @param calendar ArrayList of calendar dates in string format.
     * @param indexOfDay index of the day of the month.
     */
    private static void printTaskLectureForWeek(ArrayList<ArrayList<String>>
        calendarTasks, ArrayList<ArrayList<String>> calendarLectures,
        ArrayList<String> calendar, int indexOfDay) {
        int calendarRow = 0;
        while (calendarRow < 2) {
            System.out.print(SEPARATOR_DISPLAY);
            for (int day = INDEX_ZERO; day < NUMBER_OF_DAYS_IN_WEEK; day++) {
                printLectureCalendar(calendarLectures, calendar, indexOfDay, calendarRow, day);
            }
            System.out.println();
            calendarRow++;
        }
        int calendarRow2 = 0;
        System.out.println(LECTURE_TASK_SPLIT);
        while (calendarRow2 < 2) {
            System.out.print(SEPARATOR_DISPLAY);
            for (int day = INDEX_ZERO; day < NUMBER_OF_DAYS_IN_WEEK; day++) {
                printTasksCalendar(calendarTasks, calendar, indexOfDay, calendarRow2, day);
            }
            System.out.println();
            calendarRow2++;
        }
    }

    private static void printTasksCalendar(ArrayList<ArrayList<String>>
        calendarTasks, ArrayList<String> calendarDates, int indexOfDay, int calendarRow, int day) {
        String currentDayStringFormat = calendarDates.get(indexOfDay + day).trim();
        if (!currentDayStringFormat.equals(EMPTY_SPACE)
            && calendarTasks.get(Integer.parseInt(currentDayStringFormat)).size() > calendarRow) {
            int currentDay = Integer.parseInt(currentDayStringFormat);
            String taskName = calendarTasks.get(currentDay).get(calendarRow);
            if (taskName.length() > 15) {
                taskName = taskName.substring(INDEX_ZERO, 15);
            } else {
                taskName = String.format("%-" + 15 + "s", taskName);
            }
            System.out.print(EMPTY_SPACE + taskName + TASK_FORMATTER);
        } else {
            Ui.printEmptyTaskSpot();
        }
    }

    private static void printLectureCalendar(ArrayList<ArrayList<String>>
        calendarLectures, ArrayList<String> calendarDates,
        int indexOfDay, int calendarRow, int day) {
        String currentDayStringFormat = calendarDates.get(indexOfDay + day).trim();
        if (!currentDayStringFormat.equals(EMPTY_SPACE)
            && calendarLectures.get(Integer.parseInt(currentDayStringFormat)).size() > calendarRow) {
            int currentDay = Integer.parseInt(currentDayStringFormat);
            String moduleLecture = calendarLectures.get(currentDay).get(calendarRow) + " lecture";
            if (moduleLecture.length() > 15) {
                moduleLecture = moduleLecture.substring(INDEX_ZERO, 13) + "..";
            } else {
                moduleLecture = String.format("%-" + 15 + "s", moduleLecture);
            }
            System.out.print(EMPTY_SPACE + moduleLecture + TASK_FORMATTER);
        } else {
            Ui.printEmptyTaskSpot();
        }
    }

}