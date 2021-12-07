package seedu.duke.commands.calendar;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.calendar.CalendarIndexNotFoundException;
import seedu.duke.exceptions.calendar.IncorrectNumberOfArgumentsException;
import seedu.duke.logger.ClickLogger;
import seedu.duke.parser.schedule.ParserSchedule;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.schedule.Schedule;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.duke.constants.Messages.MONTH_LOWER_LIMIT;
import static seedu.duke.constants.Messages.MONTH_UPPER_LIMIT;
import static seedu.duke.constants.Messages.TOTAL_SIZE;

//@@author swatimahadevan

/**
 * Represents the class to display of calendar.
 */
public class DisplayCommand extends Command {
    private int year;
    private int month;
    private YearMonth inputYearMonth;
    private String[] yearMonthArguments;
    private ArrayList<ArrayList<String>> calendarTasks = new ArrayList<>(TOTAL_SIZE);
    private ArrayList<ArrayList<String>> calendarLectures = new ArrayList<>(TOTAL_SIZE);

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public DisplayCommand() {
        syntax = "calendar display [MM-YYYY]";
    }

    /**
     * Constructor for DisplayCommand class.
     *
     * @param input The input from the user in String format.
     */
    public DisplayCommand(String input) {
        helpMessage = "Display Calendar";
        syntax = "calendar display MM-YYYY";

        Schedule.intializeCalendarDayTasksList(calendarTasks);
        Schedule.intializeCalendarDayLectureList(calendarLectures);
        try {
            this.yearMonthArguments = ParserSchedule.parseCalendarCommand(input);
            this.year = Integer.parseInt(yearMonthArguments[1]);
            this.month = Integer.parseInt(yearMonthArguments[0]);
            if ((month < MONTH_LOWER_LIMIT || month > MONTH_UPPER_LIMIT)) {
                System.out.println("Month is invalid and "
                        + "I will therefore display the calendar for the current month!");
                this.inputYearMonth = YearMonth.now();
            } else {
                this.inputYearMonth = YearMonth.of(year, month);
            }
            Ui.printCalenderTitle(inputYearMonth);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            ClickLogger.getNewLogger().log(Level.WARNING, "Calendar display failed...");
            Ui.printInvalidCalendarInput();
        }
    }


    /**
     * Executes display of calendar command.
     *
     * @param ui      The component of CLICK that deals with the interaction with the user.
     * @param storage The component of CLICK that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        try {
            Schedule.arrangeTaskList(storage.tasksList, calendarTasks, this.month, this.year);
            Schedule.arrangeLectureList(storage.lectureList, calendarLectures, this.month, this.year);
            Schedule.displayCalendar(inputYearMonth, calendarTasks, calendarLectures);
        } catch (NullPointerException e) {
            //This exception throws an error message prior to this.
        }
    }
}