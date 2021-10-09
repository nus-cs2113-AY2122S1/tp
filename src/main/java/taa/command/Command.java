package taa.command;

import taa.CustomException;
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

    public abstract void execute(ModuleList modules, Ui ui) throws CustomException;

    /**
     * Checks if argumentMap contains all the argument keys.
     *
     * @return true if argumentMap contains all keys, else false.
     */
    protected boolean checkArgumentMap() {
        for (String key : argumentKeys) {
            if (!argumentMap.containsKey(key)) {
                return false;
            }
        }

        return true;
    }
}
