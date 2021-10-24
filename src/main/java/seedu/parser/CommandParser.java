package seedu.parser;

import seedu.command.EventCommand;
import seedu.exceptions.EventException;
import seedu.command.AddCommand;
import seedu.command.CalculateCapCommand;
import seedu.command.CheckCommand;
import seedu.command.ClearCommand;
import seedu.command.Command;
import seedu.command.DeleteCommand;
import seedu.command.ExitCommand;
import seedu.command.HelpCommand;
import seedu.command.InvalidCommand;
import seedu.command.RemoveCommand;
import seedu.command.SearchCommand;
import seedu.command.ShowCommand;
import seedu.command.StoreResultsCommand;
import seedu.command.TimetableCommand;
import seedu.command.UpdateCommand;
import seedu.command.flags.SearchFlags;
import seedu.duke.Duke;
import seedu.exceptions.UniModsException;
import seedu.module.Module;
import seedu.timetable.Timetable;
import seedu.timetable.TimetableUserItem;
import seedu.ui.TextUi;

public class CommandParser {
    private static final Integer MIN_EVENT_LENGTH = 14;
    private static final Integer EVENT_DESCRIPTION = 6;
    private static final Integer EVENT_START_INDEX = 9;
    private static final Integer EVENT_END_INDEX = 5;
    private static final Integer EVENT_END_END = 4;
    private static final Integer SEARCH_LENGTH = 6;
    private static final Integer CHECK_LENGTH = 5;
    private static final Integer SHOW_LENGTH = 4;
    private static final Integer ADD_LENGTH = 3;
    public static final Integer DELETE_LENGTH = 6;
    public static final Integer REMOVE_LENGTH = 6;
    public static final Integer STORE_LENGTH = 5;
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    private static final Integer TWO = 2;
    private static final String FLAG = "-";
    private static final String DELIMITER_GREATER_SIGN = ">";
    private static final String EVENT_KEYWORD = "/at";
    private static final String MONDAY = "Monday";
    private static final String TUESDAY = "Tuesday";
    private static final String WEDNESDAY = "Wednesday";
    private static final String THURSDAY = "Thursday";
    private static final String FRIDAY = "Friday";
    private static final String SATURDAY = "Saturday";
    private static final String SUNDAY = "Sunday";
    private static final String EXIT = "exit";
    private static final String UPDATE = "update";
    private static final String SEARCH = "search";
    private static final String SHOW = "show";
    private static final String TIMETABLE = "timetable";
    private static final String ADD = "add";
    private static final String EVENT = "event";
    private static final String HELP = "help";
    private static final String DELETE = "delete";
    private static final String CHECK = "check";
    private static final String CLEAR = "clear";
    private static final String STORE = "store";
    private static final String CALCULATE = "calculate";
    private static final String REMOVE = "remove";
    private static final String GAP = "";

    private String grade = "";
    private String moduleCode = "";
    private String gradeType = "";
    public static boolean isErrorThrown = false;

    public Command parseCommand(String text, Timetable timetable) {
        Command command;
        text = text.trim();
        String lowerCaseText = text.toLowerCase();

        if (text.equalsIgnoreCase(EXIT)) {
            command = new ExitCommand();
        } else if (lowerCaseText.startsWith(UPDATE)) {
            command = new UpdateCommand();
        } else if (lowerCaseText.startsWith(SEARCH)) {
            command = parseSearchCommand(text);
        } else if (lowerCaseText.startsWith(SHOW)) {
            command = parseShowCommand(text);
        } else if (lowerCaseText.startsWith(TIMETABLE)) {
            command = new TimetableCommand(Duke.timetable);
        } else if (lowerCaseText.startsWith(ADD)) {
            command = parseAddCommand(text, timetable);
        } else if (lowerCaseText.startsWith(EVENT)) {
            command = parseEventCommand(text, timetable);
        } else if (lowerCaseText.startsWith(HELP)) {
            command = new HelpCommand();
        } else if (lowerCaseText.startsWith(DELETE)) {
            command = parseDeleteCommand(text, timetable);
        } else if (lowerCaseText.startsWith(CHECK)) {
            command = parseCheckCommand(text);
        } else if (lowerCaseText.startsWith(CLEAR)) {
            command = parseClearCommand(timetable);
        } else if (lowerCaseText.startsWith(STORE)) {
            command = parseStoreResultsCommand(text);
        } else if (lowerCaseText.startsWith(CALCULATE)) {
            command = new CalculateCapCommand();
        } else if (lowerCaseText.startsWith(REMOVE)) {
            command = parseRemoveCommand(text);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    /**
     * Correct Format event Read Micah /at Monday 1600-1800.
     *
     * @param text the user input
     * @param timetable the user's current timetable schedule
     * @return returns Event Command with a timetable item to be added into timetable
     */
    private Command parseEventCommand(String text, Timetable timetable) {
        Command event = null;
        try {
            event = checkEventCommand(text, timetable);
        } catch (EventException e) {
            e.printMessage();
        }
        return event;
    }

    /**
     * Function parses user input into Event Command through a series of verifications
     * to ensure that the input has the correct parameters for an Event Command.
     *
     * @param text the user's input
     * @param timetable the user's current timetable schedule
     * @return returns Event Command with a timetable item to be added into timetable
     * @throws EventException If the user's input is invalid
     */
    private Command checkEventCommand(String text, Timetable timetable) throws EventException {
        int textLength = text.length();
        if (textLength <= MIN_EVENT_LENGTH) {
            throw new EventException("Don't be a joker and type a valid event Command");
        }

        if (!text.contains(EVENT_KEYWORD)) {
            throw new EventException("Event Request Does not Contain /at");
        }
        int dividerPosition = text.indexOf(EVENT_KEYWORD);

        String description = text.substring(EVENT_DESCRIPTION, dividerPosition).trim();
        int descriptionLength = description.length();
        if (descriptionLength == ZERO) {
            throw new EventException("Event Request Does Not Contain A Description");
        }

        String date;
        try {
            date = getDate(text);
        } catch (EventException e) {
            throw new EventException("Invalid Date Format");
        }

        String startTime = text.substring(textLength - EVENT_START_INDEX, textLength - EVENT_END_INDEX);
        if (Integer.parseInt(startTime) < ZERO) {
            throw new EventException("Invalid start time you idiot");
        }

        String endTime = text.substring(textLength - EVENT_END_END);
        if (Integer.parseInt(endTime) < ZERO) {
            throw new EventException("Maybe if you could just follow simple instructions, she wouldn't have left you");
        }

        TimetableUserItem event = new TimetableUserItem(description, date, startTime, endTime, description);
        return new EventCommand(event, timetable);
    }

    /**
     * Parses user input into a RemoveCommand.
     *
     * @param text User input.
     * @return RemoveCommand with module code that is to be removed from the transcript.
     */
    public Command parseRemoveCommand(String text) {
        String moduleToBeRemoved = (text.substring(REMOVE_LENGTH).trim()).toUpperCase();
        return new RemoveCommand(moduleToBeRemoved);
    }


    /**
     * Parses timetable into a ClearCommand.
     *
     * @param timetable User's current timetable schedule.
     * @return ClearCommand with the timetable to be cleared.
     */
    public Command parseClearCommand(Timetable timetable) {
        return new ClearCommand(timetable);
    }

    /**
     * Parses user input into a StoreResultsCommand.
     *
     * @param text User input.
     * @return StoreResultsCommand with necessary arguments to store the grades in the Transcript
     */
    public Command parseStoreResultsCommand(String text) {
        text = text.substring(STORE_LENGTH).trim();
        String[] split = text.split(DELIMITER_GREATER_SIGN);
        try {
            checkCommand(split);
            grade = split[ZERO].trim().toUpperCase();
            gradeType = Module.checkGradeType(grade);
            moduleCode = split[ONE].trim().toUpperCase();
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
        }
        return new StoreResultsCommand(grade, moduleCode, gradeType, isErrorThrown);
    }


    public static void checkCommand(String[] split) throws UniModsException {
        isErrorThrown = false;
        if (split.length < TWO || split[ZERO].equals(GAP) || split[ONE].equals(GAP)) {
            isErrorThrown = true;
            throw new UniModsException(TextUi.ERROR_INVALID_RESULT_COMMAND);
        }
    }

    /**
     * Parses user input into a DeleteCommand.
     *
     * @param text User input.
     * @param timetable User's current timetable schedule.
     * @return DeleteCommand with module code that is to be deleted from the timetable.
     */
    public Command parseDeleteCommand(String text, Timetable timetable) {
        String moduleToBeDeleted = (text.substring(DELETE_LENGTH).trim()).toUpperCase();
        return new DeleteCommand(moduleToBeDeleted, timetable);
    }


    /**
     * Parses user input into a SearchCommand.
     *
     * @param text User input.
     * @return SearchCommand with searchTerm and all flags.
     */
    private Command parseSearchCommand(String text) {
        if (text.toLowerCase().contains(FLAG)) {
            return parseSearchCommandWithFlag(text);
        }
        assert !text.contains(FLAG) : "This line should not be hit with a flag.";
        String searchTerm = text.substring(SEARCH_LENGTH).trim();
        return new SearchCommand(searchTerm, new SearchFlags());
    }

    /**
     * Parses user input with detected flags into a SearchCommand.
     *
     * @param text User input.
     * @return SearchCommand with searchTerm and all flags.
     */
    private Command parseSearchCommandWithFlag(String text) {
        assert text.contains(FLAG) : "String should contain flag";
        int firstFlagPos = text.indexOf(FLAG);
        String searchTerm = text.substring(SEARCH_LENGTH, firstFlagPos).trim();
        String[] flags = text.substring(firstFlagPos).split(FLAG);
        SearchFlags searchFlags = FlagParser.parseSearchFlags(flags);
        return new SearchCommand(searchTerm, searchFlags);
    }

    private Command parseCheckCommand(String text) {
        String moduleToBeChecked = text.substring(CHECK_LENGTH).trim();
        return new CheckCommand(moduleToBeChecked, Duke.getProfileInUse());
    }

    /**
     * Parses user input into a ShowCommand.
     *
     * @param text User input.
     * @return ShowCommand with searchTerm.
     */
    private Command parseShowCommand(String text) {
        String str = text.substring(SHOW_LENGTH).trim();
        return new ShowCommand(str);
    }

    /**
     * Parses user input into an AddCommand.
     *
     * @param text User input.
     * @param timetable User's current timetable schedule.
     * @return AddCommand with the moduleCode that is to be added
     */
    private Command parseAddCommand(String text, Timetable timetable) {
        text = text.substring(ADD_LENGTH).trim();
        String moduleCode = text.toUpperCase();
        return new AddCommand(moduleCode, timetable);
    }

    private String getDate(String text) throws EventException {
        String date;
        if (text.contains(MONDAY)) {
            date = MONDAY;
        } else if (text.contains(TUESDAY)) {
            date = TUESDAY;
        } else if (text.contains(WEDNESDAY)) {
            date = WEDNESDAY;
        } else if (text.contains(THURSDAY)) {
            date = THURSDAY;
        } else if (text.contains(FRIDAY)) {
            date = FRIDAY;
        } else if (text.contains(SATURDAY)) {
            date = SATURDAY;
        } else if (text.contains(SUNDAY)) {
            date = SUNDAY;
        } else {
            throw new EventException("Invalid Date Format");
        }
        return date;
    }
}
