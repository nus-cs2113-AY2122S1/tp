package terminus.common;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * CommonFormat class that contains formats that are used across different packages.
 */
public class CommonFormat {

    /*
     * Command names 
     */
    public static final String COMMAND_NOTE = "note";
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_VIEW = "view";
    public static final String COMMAND_BACK = "back";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_SCHEDULE = "schedule";
    public static final String COMMAND_QUESTION = "question";
    public static final String COMMAND_TEST = "test";
    public static final String COMMAND_TIMETABLE = "timetable";
    public static final String COMMAND_MODULE = "module";
    public static final String COMMAND_GO = "go";
    public static final String COMMAND_EXPORT = "export";
    public static final String COMMAND_UPDATE = "update";
    public static final String COMMAND_RELOAD = "reload";

    public static final String LOCAL_TIME_FORMAT = "HH:mm";

    /*
     * Command Formats 
     */
    public static final String COMMAND_DELETE_FORMAT = COMMAND_DELETE + " <item number>";
    public static final String COMMAND_VIEW_FORMAT = COMMAND_VIEW + " {item number}";
    public static final String COMMAND_ADD_SCHEDULE_FORMAT = COMMAND_ADD + " \"<link description>\" "
            + "\"<day>\" \"<start_time " + LOCAL_TIME_FORMAT + ">\" \"<duration>\" \"<zoom_link>\"";
    public static final String COMMAND_ADD_NOTE_FORMAT = COMMAND_ADD + " \"<note name>\" \"<note content>\"";
    public static final String COMMAND_ADD_QUESTION_FORMAT = COMMAND_ADD + " \"<question>\" \"<answer>\"";
    public static final String COMMAND_TEST_QUESTION_FORMAT = COMMAND_TEST + " {question count}";
    public static final String COMMAND_TIMETABLE_FORMAT = COMMAND_TIMETABLE + " {day}";
    public static final String COMMAND_UPDATE_MODULE_FORMAT = COMMAND_UPDATE + " <index> <new module name>";
    public static final String COMMAND_ADD_MODULE_FORMAT = "add \"<module name>\"";
    public static final String COMMAND_MODULE_FORMAT = COMMAND_MODULE;
    public static final String COMMAND_VIEW_MODULE_FORMAT = COMMAND_VIEW;

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String SPACE_NEGATED_DELIMITER = "\\S+";
    
    /*
     * Notes storage setting 
     */
    public static final String EXTENSION_TEXT_FILE = ".txt";
    public static final String CONTENT_TYPE_TEXT_FILE = "text/plain";

    public static final ArrayList<Character> ILLEGAL_CHARACTERS = new ArrayList<>(
            Arrays.asList('/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':', '.'));
    public static final int MAX_FILENAME_LENGTH = 30;
    public static final int STARTING_ASCII = 32;
    public static final int ENDING_ASCII = 126;
    public static final long MAX_FILE_SIZE = 1000000;
    
    /*
     * PDF settings
     */
    public static final String PDF_FORMAT = ".pdf";
    public static final String FONT_NAME = "Arial";
    public static final int FONT_HEADER_SIZE = 14;
    public static final int FONT_SIZE = 11;
    public static final String UPDATE_MODULE_REGEX_FORMAT = "(?<index>\\d+)\\s+\"(?<newName>.*?)\"";
    public static final String COMMAND_GO_FORMAT = "go <module name>";
    public static final String QUESTION_FORMAT_CHECK = "^[123e]$";
    public static final String QUOTE_REGEX_DELIMITER = "\"(.*?)\"";
}
