package taa.command;

//@@author leyondlee

import taa.teachingclass.ClassList;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Parser;
import taa.Ui;

import java.util.HashMap;

public abstract class Command {
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_LIST_CLASSES = "list_classes";
    public static final String COMMAND_LIST_STUDENTS = "list_students";
    public static final String COMMAND_LIST_ASSESSMENTS = "list_assessments";
    public static final String COMMAND_LIST_ATTENDANCE = "list_attendance";
    public static final String COMMAND_LIST_LESSON_ATTENDANCE = "list_lesson_attendance";
    public static final String COMMAND_ADD_CLASS = "add_class";
    public static final String COMMAND_EDIT_CLASS = "edit_class";
    public static final String COMMAND_DELETE_CLASS = "delete_class";
    public static final String COMMAND_FIND_STUDENT = "find_student";
    public static final String COMMAND_ADD_STUDENT = "add_student";
    public static final String COMMAND_EDIT_STUDENT = "edit_student";
    public static final String COMMAND_DELETE_STUDENT = "delete_student";
    public static final String COMMAND_SET_ATTENDANCE = "set_attendance";
    public static final String COMMAND_DELETE_ATTENDANCE = "delete_attendance";
    public static final String COMMAND_ADD_ASSESSMENT = "add_assessment";
    public static final String COMMAND_DELETE_ASSESSMENT = "delete_assessment";
    public static final String COMMAND_EDIT_ASSESSMENT = "edit_assessment";
    public static final String COMMAND_LIST_MARKS = "list_marks";
    public static final String COMMAND_AVERAGE_MARK = "average_mark";
    public static final String COMMAND_MEDIAN_MARK = "median_mark";
    public static final String COMMAND_SET_MARKS = "set_mark";
    public static final String COMMAND_EDIT_MARK = "edit_mark";
    public static final String COMMAND_DELETE_MARK = "delete_mark";
    public static final String COMMAND_SORT_BY_SCORES = "sort_by_scores";
    public static final String COMMAND_SET_COMMENT = "set_comment";
    public static final String COMMAND_DELETE_COMMENT = "delete_comment";
    public static final String COMMAND_LIST_COMMENT = "list_comments";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_ARCHIVE = "archive";
    public static final String COMMAND_RESET = "reset";

    protected static final String PROMPT_WITHIN_COMMAND = "> ";

    // Common messages
    protected static final String MESSAGE_UNKNOWN_USAGE = "Unknown usage.";
    protected static final String MESSAGE_CLASS_NOT_FOUND = "Class not found.";
    protected static final String MESSAGE_INVALID_STUDENT_INDEX = "Invalid student index.";
    protected static final String MESSAGE_INVALID_LESSON_NUMBER = "Invalid lesson number.";
    protected static final String MESSAGE_LESSON_NUMBER_EXCEEDS_MAX = "Lesson number exceeds 1000.";
    protected static final String MESSAGE_INVALID_LESSON_NUMBER_WITH_INFO = "Invalid lesson number for student of "
            + "index %s (%s).";
    protected static final String MESSAGE_INVALID_ASSESSMENT_NAME = "Invalid assessment name.";
    protected static final String MESSAGE_INVALID_MARKS = "Invalid marks. Marks must be a double with at most 2 "
            + "decimal points.";
    protected static final String MESSAGE_NO_STUDENTS = "There are no students in this class";
    protected static final String MESSAGE_NO_MARKS = "This student has not been marked yet.";
    protected static final String MESSAGE_ALREADY_MARKED = "This student has already been marked.";
    protected static final String MESSAGE_NO_COMMENT_ADDED = "This student has no comment set.";
    protected static final String MESSAGE_DATA_REMOVED = "All data have been removed.";
    protected static final String MESSAGE_ABORTED = "Aborted.";
    protected static final String MESSAGE_INVALID_CLASS_ID = "Invalid class ID. Class ID cannot be empty or "
            + "contain spaces.";

    // Common message formats
    protected static final String MESSAGE_FORMAT_GENERIC_USAGE = "%s";
    protected static final String MESSAGE_FORMAT_MISSING_ARGUMENT = "Missing Argument(s).\n%s";
    protected static final String MESSAGE_FORMAT_USAGE_MESSAGE = "Usage: %s";

    protected String argument;
    protected boolean isExit;
    protected String[] argumentKeys;
    protected HashMap<String, String> argumentMap;
    protected boolean includeEmptyValues;

    public Command(String argument) {
        this(argument, null);
    }

    public Command(String argument, String[] argumentKeys) {
        this(argument, argumentKeys, false);
    }

    public Command(String argument, String[] argumentKeys, boolean includeEmptyValues) {
        this.argument = argument;
        this.isExit = false;
        this.argumentKeys = argumentKeys;
        this.argumentMap = null;
        this.includeEmptyValues = includeEmptyValues;
    }

    protected abstract String getUsage();

    public abstract void checkArgument() throws TaaException;

    public abstract void execute(ClassList classList, Ui ui, Storage storage) throws TaaException;

    public void parseArgument() throws TaaException {
        argumentMap = Parser.getArgumentsFromString(argument, argumentKeys, includeEmptyValues);
    }

    public boolean isExit() {
        return isExit;
    }

    protected String getUsageMessage() {
        return String.format(MESSAGE_FORMAT_USAGE_MESSAGE, getUsage());
    }

    protected String getMissingArgumentMessage() {
        String usageMessage = getUsageMessage();
        if (usageMessage == null) {
            usageMessage = MESSAGE_UNKNOWN_USAGE;
        }

        return String.format(MESSAGE_FORMAT_MISSING_ARGUMENT, usageMessage);
    }

    /**
     * Checks if there are any missing arguments.
     *
     * @return true if there no missing arguments, else false.
     */
    protected boolean hasAllArguments() {
        for (String key : argumentKeys) {
            if (!argumentMap.containsKey(key)) {
                return false;
            }
        }

        return true;
    }
}
