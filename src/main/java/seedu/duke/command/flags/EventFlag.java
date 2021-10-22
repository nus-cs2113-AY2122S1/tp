package seedu.duke.command.flags;

public class EventFlag extends TaskFlag {
    public static final String START_DATE = "start";
    public static final String END_DATE = "end";

    public static final String[] REQUIRED_FLAGS = {DESCRIPTION, START_DATE, END_DATE};
}
