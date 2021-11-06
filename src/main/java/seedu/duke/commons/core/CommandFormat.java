package seedu.duke.commons.core;

import static seedu.duke.ui.Ui.PADDING;

//@@author Roycius
public class CommandFormat {
    public static final String ADD_TASK_FORMAT =
            "add task [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}";

    public static final String ADD_LESSON_FORMAT =
            "add lesson [TITLE] -d [DAY_OF_THE_WEEK] -s [START_TIME] -e [END_TIME] -l {MEETING_URL}";

    public static final String ADD_MODULE_FORMAT =
            "add module [MODULE_CODE]";

    public static final String LIST_TASK_FORMAT =
            "list task {PERIOD/PRIORITY}";

    public static final String LIST_LESSON_FORMAT =
            "list lesson {PERIOD}";

    public static final String LIST_MODULE_FORMAT =
            "list module";

    public static final String DELETE_TASK_FORMAT =
            "delete task [INDEX]";

    public static final String DELETE_LESSON_FORMAT =
            "delete lesson [INDEX]";

    public static final String DELETE_MODULE_FORMAT =
            "delete module [MODULE_CODE]";

    public static final String DONE_TASK_FORMAT =
            "done task [INDEX]";

    public static final String FIND_TASK_FORMAT =
            "find task [KEYWORD]";

    public static final String FIND_LESSON_FORMAT =
            "find lesson [KEYWORD]";

    public static final String FIND_MODULE_FORMAT =
            "find module [MODULE_CODE] {verbose}";

    public static final String HELP_FORMAT =
            "help";

    public static final String SET_GRADE_FORMAT =
            "set grade [MODULE_CODE] [GRADE]";

    public static final String LAUNCH_LESSON_FORMAT =
            "launch lesson [INDEX]";

    public static final String EXIT_FORMAT =
            "exit";

    /**
     * Prompts the user regarding wrong formatting and suggests related commands.
     *
     * @param commandFormats the command formats to be suggested
     * @return a string of the message to be printed
     */
    public static String promptFormat(String... commandFormats) {
        StringBuilder s = new StringBuilder();
        s.append("Wrong format! Here are some possible related commands:");
        for (String commandFormat : commandFormats) {
            s.append(System.lineSeparator()).append(PADDING).append(commandFormat);
        }
        return s.toString();
    }
}
