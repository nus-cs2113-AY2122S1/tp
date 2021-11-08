package seedu.duke.commons.core;

//@@author richwill28
/**
 * Contains a list of command flags for parsing user response.
 */
public final class CommandFlag {
    public static final String DAY = "-d";
    public static final String END = "-e";
    public static final String GRADE = "-g";
    public static final String INFORMATION = "-i";
    public static final String LINK = "-l";
    public static final String PRIORITY = "-p";
    public static final String START = "-s";

    public static final String LESSON = "-d |-s |-e |-l ";
    public static final String MODULE = "-g ";
    public static final String TASK = "-d |-p |-i ";
}
