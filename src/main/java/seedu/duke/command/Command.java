package seedu.duke.command;

import seedu.duke.CustomException;
import seedu.duke.Ui;
import seedu.duke.module.ModuleList;

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
    public static final String COMMAND_ADD_STUDENT = "add_student";
    public static final String COMMAND_EDIT_STUDENT = "edit_student";
    public static final String COMMAND_DELETE_STUDENT = "delete_student";
    public static final String COMMAND_SET_ATTENDANCE = "set_attendance";
    public static final String COMMAND_ADD_ASSESSMENT = "add_assessment";
    public static final String COMMAND_SET_MARKS = "set_marks";

    protected String argument;
    protected boolean isExit;

    public Command(String argument) {
        this.argument = argument;
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(ModuleList modules, Ui ui) throws CustomException;

    protected static boolean checkArgumentsMap(HashMap<String, String> argumentsMap, String[] argumentKeys) {
        for (String key : argumentKeys) {
            if (!argumentsMap.containsKey(key)) {
                return false;
            }
        }

        return true;
    }
}
