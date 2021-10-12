package taa.command;

import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Parser;
import taa.Ui;
import taa.module.ModuleList;

import java.util.HashMap;

public abstract class Command {
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_LIST_MODULES = "list_modules";
    public static final String COMMAND_LIST_STUDENTS = "list_students";
    public static final String COMMAND_LIST_ASSESSMENTS = "list_assessments";
    public static final String COMMAND_LIST_ATTENDANCE = "list_attendance";
    public static final String COMMAND_LIST_MARKS = "list_marks";
    public static final String COMMAND_AVERAGE_MARKS = "average_marks";
    public static final String COMMAND_ADD_MODULE = "add_module";
    public static final String COMMAND_FIND_STUDENT = "find_student";
    public static final String COMMAND_ADD_STUDENT = "add_student";
    public static final String COMMAND_EDIT_STUDENT = "edit_student";
    public static final String COMMAND_DELETE_STUDENT = "delete_student";
    public static final String COMMAND_SET_ATTENDANCE = "set_attendance";
    public static final String COMMAND_ADD_ASSESSMENT = "add_assessment";
    public static final String COMMAND_SET_MARKS = "set_marks";

    // Common messages
    protected static final String MESSAGE_UNKNOWN_USAGE = "Unknown usage.";
    protected static final String MESSAGE_MODULE_NOT_FOUND = "Module not found.";
    protected static final String MESSAGE_INVALID_STUDENT_INDEX = "Invalid student index.";
    protected static final String MESSAGE_INVALID_LESSON_INDEX = "Invalid lesson index.";
    protected static final String MESSAGE_INVALID_ASSESSMENT_NAME = "Invalid assessment name.";
    protected static final String MESSAGE_NO_STUDENTS = "There are no students in this module";

    // Common message formats
    protected static final String MESSAGE_FORMAT_GENERIC_USAGE = "Usage: %s";
    protected static final String MESSAGE_FORMAT_MISSING_ARGUMENT = "Missing Argument(s).\n%s";

    protected String argument;
    protected boolean isExit;
    protected String[] argumentKeys;
    protected HashMap<String,String> argumentMap;

    public Command(String argument) {
        this(argument, null);
    }

    public Command(String argument, String[] argumentKeys) {
        this.argument = argument;
        this.isExit = false;
        this.argumentKeys = argumentKeys;
        this.argumentMap = Parser.getArgumentsFromString(argument, argumentKeys);
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException;

    protected abstract String getUsageMessage();

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
    protected boolean checkArguments() {
        for (String key : argumentKeys) {
            if (!argumentMap.containsKey(key)) {
                return false;
            }
        }

        return true;
    }
}
