package terminus.common;


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
    public static final String COMMAND_QUESTION = "question";
    public static final String COMMAND_TEST = "test";

    public static final String LOCAL_TIME_FORMAT = "HH:mm";

    public static final String COMMAND_DELETE_FORMAT = COMMAND_DELETE + " <item number>";
    public static final String COMMAND_VIEW_FORMAT = COMMAND_VIEW + " {item number}";
    public static final String COMMAND_ADD_SCHEDULE_FORMAT = COMMAND_ADD + " \"<link description>\" "
            + "\"<day>\" \"<start_time " + LOCAL_TIME_FORMAT + ">\" \"<zoom_link>\"";
    public static final String COMMAND_ADD_NOTE_FORMAT = COMMAND_ADD + " \"<note name>\" \"<note content>\"";
    public static final String COMMAND_ADD_QUESTION_FORMAT = COMMAND_ADD + " \"<question>\" \"<answer>\"";
    public static final String COMMAND_TEST_QUESTION_FORMAT = COMMAND_TEST + " {question count}";

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String COMMAND_MODULE_FORMAT = "module";
    public static final String COMMAND_ADD_MODULE_FORMAT = "add \"<module name>\"";
    public static final String COMMAND_VIEW_MODULE_FORMAT = "view";
    public static final String SPACE_NEGATED_DELIMITER = "\\S+";
}
