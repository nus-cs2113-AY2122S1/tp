package terminus.common;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * CommonFormat class that contains formats that are used across different packages.
 */
public class CommonFormat {

    public static final String COMMAND_NOTE = "note";
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_VIEW = "view";
    public static final String COMMAND_BACK = "back";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_SCHEDULE = "schedule";

    public static final String LOCAL_TIME_FORMAT = "HH:mm";

    public static final String COMMAND_DELETE_FORMAT = COMMAND_DELETE + " <item number>";
    public static final String COMMAND_VIEW_FORMAT = COMMAND_VIEW + " {item number}";
    public static final String COMMAND_ADD_SCHEDULE_FORMAT = COMMAND_ADD + " \"<link description>\" "
            + "\"<day>\" \"<start_time " + LOCAL_TIME_FORMAT + ">\" \"<zoom_link>\"";
    public static final String COMMAND_ADD_NOTE_FORMAT = COMMAND_ADD + " \"<note name>\" \"<note content>\"";

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String COMMAND_MODULE_FORMAT = "module";
    public static final String COMMAND_ADD_MODULE_FORMAT = "add \"<module name>\"";
    public static final String COMMAND_VIEW_MODULE_FORMAT = "view";
    public static final String SPACE_NEGATED_DELIMITER = "\\S+";


    public static final String EXTENSION_TEXT_FILE = ".txt";

    public static final ArrayList<Character> ILLEGAL_CHARACTERS = new ArrayList<>(
            Arrays.asList('/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':', '.'));
    public static final int MAX_FILENAME_LENGTH = 30;
    public static final int STARTING_ASCII = 32;
    public static final int ENDING_ASCII = 126;
    public static final long MAX_FILE_SIZE = 1000000;

    public static final String COMMAND_MODULE = "module";
    public static final String COMMAND_GO = "go";

}
