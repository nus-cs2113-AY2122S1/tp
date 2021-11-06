package seedu.parser;

import seedu.command.AddCommand;
import seedu.command.CalculateCapCommand;
import seedu.command.ChangeSemesterCommand;
import seedu.command.CheckCommand;
import seedu.command.ClearCommand;
import seedu.command.Command;
import seedu.command.DeleteCommand;
import seedu.command.EditCommand;
import seedu.command.ExitCommand;
import seedu.command.HelpCommand;
import seedu.command.InvalidCommand;
import seedu.command.RemoveCommand;
import seedu.command.SearchCommand;
import seedu.command.ShowCommand;
import seedu.command.StoreResultsCommand;
import seedu.command.TimetableCommand;
import seedu.command.TranscriptCommand;
import seedu.command.UpdateCommand;
import seedu.command.flags.AddFlag;
import seedu.command.flags.ClearFlag;
import seedu.command.flags.SearchFlags;
import seedu.unimods.UniMods;
import seedu.exceptions.UniModsException;
import seedu.module.Module;
import seedu.timetable.Timetable;
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

    /**
     * Parses user input into different Command objects depending on the input.
     *
     * @param text      User input.
     * @param timetable Timetable object for timetable commands.
     * @return command A Command object which class depends on the input.
     */
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
            boolean showUserItemsOnly = false;
            if (lowerCaseText.contains("-u")) {
                showUserItemsOnly = true;
            }
            command = new TimetableCommand(timetable, showUserItemsOnly);
        } else if (lowerCaseText.equals("add")) {
            command = parseAddCommand(timetable);
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
        } else if (lowerCaseText.startsWith("transcript")) {
            command = new TranscriptCommand();
        } else if (lowerCaseText.startsWith("edit")) {
            command = new EditCommand(timetable);
        } else if (lowerCaseText.startsWith("semester")) {
            command = new ChangeSemesterCommand(timetable);
        } else {
            command = new InvalidCommand();
        }
        return command;
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
        ClearFlag clearFlag = ClearFlag.INVALID;
        try {
            clearFlag = TextUi.getClearFlag();
        } catch (UniModsException e) {
            e.printMessage();
        }
        assert clearFlag != ClearFlag.INVALID;
        return new ClearCommand(timetable, clearFlag);
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
            if (gradeType.equals("")) {
                return new InvalidCommand();
            } else {
                return new StoreResultsCommand(grade, moduleCode, gradeType, isErrorThrown);
            }
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
            return new InvalidCommand();
        }
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
     * @return DeleteeCommand with module code that is to be deleted from the timetable.
     */
    public Command parseDeleteCommand(String text, Timetable timetable) {
        String itemToBeDeleted = (text.substring(DELETE_LENGTH).trim()).toUpperCase();
        return new DeleteCommand(itemToBeDeleted, timetable);
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
        return new CheckCommand(moduleToBeChecked, UniMods.getProfileInUse());
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
     * @param timetable Timetable to add to.
     * @return AddCommand with timetable and flag.
     */
    private Command parseAddCommand(Timetable timetable) {
        AddFlag flag = AddFlag.INVALID;
        try {
            flag = TextUi.getAddFlag();
        } catch (UniModsException e) {
            e.printMessage();
        }
        assert flag != AddFlag.INVALID;
        return new AddCommand(timetable, flag);
    }
}
