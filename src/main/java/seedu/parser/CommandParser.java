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
    private static final Integer SEARCH_LENGTH = 6;
    private static final Integer CHECK_LENGTH = 5;
    private static final Integer SHOW_LENGTH = 4;
    private static final Integer ADD_LENGTH = 3;
    public static final Integer DELETE_LENGTH = 6;
    public static final Integer REMOVE_LENGTH = 6;
    public static final Integer STORE_LENGTH = 5;
    private static final String FLAG = "-";
    private static final String DELIMITER_GREATER_SIGN = ">";
    private String grade = "";
    private String moduleCode = "";
    private String gradeType = "";
    public static boolean isErrorThrown = false;


    public Command parseCommand(String text, Timetable timetable) {
        Command command;
        text = text.trim();
        String lowerCaseText = text.toLowerCase();

        if (text.equalsIgnoreCase("exit")) {
            command = new ExitCommand();
        } else if (lowerCaseText.startsWith("update")) {
            command = new UpdateCommand();
        } else if (lowerCaseText.startsWith("search")) {
            command = parseSearchCommand(text);
        } else if (lowerCaseText.startsWith("show")) {
            command = parseShowCommand(text);
        } else if (lowerCaseText.startsWith("timetable")) {
            command = new TimetableCommand(Duke.timetable);
        } else if (lowerCaseText.startsWith("add")) {
            command = parseAddCommand(text, timetable);
        } else if (lowerCaseText.startsWith("event")) {
            command = parseEventCommand(text, timetable);
        } else if (lowerCaseText.startsWith("help")) {
            command = new HelpCommand();
        } else if (lowerCaseText.startsWith("delete")) {
            command = parseDeleteCommand(text, timetable);
        } else if (lowerCaseText.startsWith("check")) {
            command = parseCheckCommand(text);
        } else if (lowerCaseText.startsWith("clear")) {
            command = parseClearCommand(timetable);
        } else if (lowerCaseText.startsWith("store")) {
            command = parseStoreResultsCommand(text);
        } else if (lowerCaseText.startsWith("calculate")) {
            command = new CalculateCapCommand();
        } else if (lowerCaseText.startsWith("remove")) {
            command = parseRemoveCommand(text);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    //Format event Read Micah /at Monday 1600-1800
    private Command parseEventCommand(String text, Timetable timetable) {
        Command event = null;
        try {
            event = checkEventCommand(text, timetable);
        } catch (EventException e) {
            e.printMessage();
        }
        return event;
    }

    private Command checkEventCommand(String text, Timetable timetable) throws EventException {
        int textLength = text.length();
        if (textLength <= 14) {
            throw new EventException("Don't be a joker and type a valid event Command");
        }

        if (!text.contains("/at")) {
            throw new EventException("Event Request Does not Contain /at");
        }
        int dividerPosition = text.indexOf("/at");

        String description = text.substring(6, dividerPosition).trim();
        int descriptionLength = description.length();
        if (descriptionLength == 0) {
            throw new EventException("Event Request Does Not Contain A Description");
        }

        String date;
        if (text.contains("Monday")) {
            date = "Monday";
        } else if (text.contains("Tuesday")) {
            date = "Tuesday";
        } else if (text.contains("Wednesday")) {
            date = "Wednesday";
        } else if (text.contains("Thursday")) {
            date = "Thursday";
        } else if (text.contains("Friday")) {
            date = "Friday";
        } else if (text.contains("Saturday")) {
            date = "Saturday";
        } else if (text.contains("Sunday")) {
            date = "Sunday";
        } else {
            throw new EventException("Invalid Date Format");
        }

        String startTime = text.substring(textLength - 9, textLength - 5);
        if (Integer.parseInt(startTime) < 0) {
            throw new EventException("Invalid start time you idiot");
        }

        String endTime = text.substring(textLength - 4, textLength);
        if (Integer.parseInt(endTime) < 0) {
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
            grade = split[0].trim().toUpperCase();
            gradeType = Module.checkGradeType(grade);
            moduleCode = split[1].trim().toUpperCase();
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
        }
        return new StoreResultsCommand(grade, moduleCode, gradeType, isErrorThrown);
    }


    public static void checkCommand(String[] split) throws UniModsException {
        isErrorThrown = false;
        if (split.length < 2 || split[0].equals("") || split[1].equals("")) {
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

}
