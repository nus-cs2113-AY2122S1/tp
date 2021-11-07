package seedu.duke.ui;

import seedu.duke.food.FoodRecord;
import seedu.duke.constants.Messages;
import seedu.duke.schedule.lecture.Lecture;
import seedu.duke.schedule.task.Task;
import seedu.duke.storage.Storage;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.constants.Messages.ADDED_TASK;
import static seedu.duke.constants.Messages.DAY_DEMARCATION;
import static seedu.duke.constants.Messages.DISPLAY_LINE;
import static seedu.duke.constants.Messages.HORIZONTAL_LINE;
import static seedu.duke.constants.Messages.INVALID_CALENDAR_INPUT;
import static seedu.duke.constants.Messages.LINE_PREFIX;
import static seedu.duke.constants.Messages.LIST_TASKS_HEADER;
import static seedu.duke.constants.Messages.LOGO;
import static seedu.duke.constants.Messages.MESSAGE_GOODBYE;
import static seedu.duke.constants.Messages.MESSAGE_GREETING;
import static seedu.duke.constants.Messages.NO_TASK_IN_DAY;

/**
 * A class that deals with interactions with the user.
 */
public class Ui {

    private static Storage storage;

    static {
        try {
            storage = new Storage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Ui ui = new Ui();

    //@@author nvbinh15
    /**
     * Reads the text entered by the user.
     *
     * @param in The Scanner to read user input.
     * @return line Full line entered by the user.
     */
    public String getUserInput(Scanner in) {
        String line = in.nextLine();
        return line;
    }

    /**
     * Prints the divider HORIZONTAL_LINE.
     */
    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the greeting message to standard output.
     */
    public static void printGreeting() {
        printLine();
        printMessage(LOGO + MESSAGE_GREETING);
        printLine();
    }

    /**
     * Prints the goodbye message to standard output.
     */
    public static void printGoodBye() {
        printLine();
        printMessage(MESSAGE_GOODBYE);
        printLine();
    }

    /**
     * Prints message to the standard output.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(LINE_PREFIX + message);
    }

    public static void printMessageSameLine(String message) {
        System.out.print(LINE_PREFIX + message);
    }

    //@@author ngnigel99
    /**
     * Prints the name of the record, as well as the calorie count.
     * @author ngnigel99
     * @param record record to be added
     */
    //TODO  remove messages, may be neater
    public static void printAddRecord(FoodRecord record)  {
        Ui.printLine();
        Ui.printMessage("Nice.  I've added "
                + record.getFoodName()
                + " to the list, with "
                + record.getCalorieCount()
                + " calories!");
        Ui.printLine();
    }

    /**
     * Prints success on clear message to user.
     *
     * @author ngnigel99
     */
    public static void printDoneClearList() {
        Ui.printLine();
        Ui.printMessage(Messages.PRINT_DONE_CLEAR_LIST);
        Ui.printLine();
    }

    public static void printAddFoodSyntax() {
        Ui.printLine();
        Ui.printMessage(Messages.PRINT_ADD_FOOD_SYNTAX);
        Ui.printLine();
    }

    public static void printNonNullInput() {
        Ui.printLine();
        Ui.printMessage(Messages.NON_NULL_INPUT);
        Ui.printLine();
    }

    public static void printOnlyIntegers() {
        Ui.printLine();
        Ui.printMessage(Messages.PRINT_NOT_AN_INT);
        Ui.printLine();
    }

    /**
     * Prints a general error message.
     * Ideally should be more specific, try not to use this
     * for exceptions - implement in further testing versions.
     */
    public static void printErrorMessageGeneral() {
        Ui.printLine();
        Ui.printMessage(Messages.PRINT_ERROR_MESSAGE_GENERAL);
        Ui.printLine();
    }

    public static void printDoneDeleteFood(FoodRecord foodRecord, int index) {
        Ui.printLine();
        Ui.printMessage(Messages.PRINT_DONE_DELETE_INDEX + " "
                +  foodRecord.getFoodName()
                + " at index: " + index);
        Ui.printLine();
    }
    //@@author ngnigel99

    //@@author SvethaMahadevan
    /**
     * Prints message to indicate note has been added.
     */
    public static void printAddedNoteMessage(String noteName) {
        ui.printLine();
        printMessage("Great you have added the note: " + noteName);
        ui.printLine();
    }

    /**
     * Prints message to indicate entry has been added.
     */
    public static void printAddedEntryMessage(String entryName) {
        ui.printLine();
        printMessage("Great you have added the entry: " + entryName);
        ui.printLine();
    }

    /**
     * Prints message to indicate that the notebook has been deleted.
     */
    public static void printDeletedNotebookMessage(int indexOfDeletedNotebook) {
        ui.printLine();
        printMessage("Great you have deleted the notebook at : " + indexOfDeletedNotebook);
        ui.printLine();
    }

    /**
     * Prints message to indicate that the entry has been deleted.
     */
    public static void printDeletedEntryMessage() {
        ui.printLine();
        printMessage("Great you have deleted the entry");
        ui.printLine();
    }

    /**
     * Prints message to indicate that the notebook had been tagged.
     */
    public static void printTaggedNotebookMessage() {
        ui.printLine();
        printMessage("Great you have tagged the notebook");
        ui.printLine();
    }
    //@@author SvethaMahadevan

    //@@author swatimahadevan
    /**
     * Prints the header of the calendar with the month and year.
     *
     * @param inputYearMonth The YearMonth object parsed from user input string.
     */
    public static void printCalenderTitle(YearMonth inputYearMonth) {
        ui.printMessage("                                       " + inputYearMonth.getMonth() + " "
                + inputYearMonth.getYear());
    }

    /**
     * Prints error message for all invalid calendar inputs
     * telling user to use 'command help'.
     */
    public static void printInvalidCalendarInput() {
        ui.printLine();
        ui.printMessage(INVALID_CALENDAR_INPUT);
        ui.printLine();
    }

    /**
     * Prints out lecture list.
     *
     * @param lectures List of lectures to be displayed.
     */
    public static void printLectureList(ArrayList<Lecture> lectures) {
        ui.printMessage("Here is your list of lectures:");
        if (!lectures.isEmpty()) {
            int i = 1;
            for (Lecture item : lectures) {
                ui.printMessage((i++) + ". " + item);
            }
        } else {
            ui.printMessage("NO Lectures!");
        }
    }

    /**
     * Prints confirmatory message after task is added successfully.
     */
    public static void printTaskAddedMessage() {
        ui.printMessage(ADDED_TASK);
    }

    /**
     * Prints out tasks list.
     *
     * @param tasks List of tasks to be displayed.
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        ui.printMessage(LIST_TASKS_HEADER);
        if (!tasks.isEmpty()) {
            int i = 1;
            for (Task item : tasks) {
                ui.printMessage((i++) + ". " + item);
            }
        } else {
            ui.printMessage("NO TASKS!");
        }
    }

    /**
     * Prints calendar lines for display.
     */
    public static void printCalendarLine() {
        System.out.println(DISPLAY_LINE);
    }

    /**
     * Prints out the vertical lines between days.
     */
    public static void printDayDemarcation() {
        System.out.print(DAY_DEMARCATION);
    }

    /**
     * Prints out the task grid for empty task days.
     */
    public static void printEmptyTaskSpot() {
        System.out.print(NO_TASK_IN_DAY);
    }
    //@@author

    public static void displayZoomLink(String moduleName, String zoomLink) {
        printMessage("Module: " + moduleName);
        printMessage("Zoom: " + zoomLink);
    }

    public static void printHelpMessage(String helpMessage, String syntax) {
        printMessage("Description: " + helpMessage);
        printMessage("Syntax: " + syntax);
    }

}
