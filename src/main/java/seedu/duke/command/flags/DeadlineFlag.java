package seedu.duke.command.flags;

public class DeadlineFlag extends TaskFlag {
    public static final String DUE_DATE = "due";

    public static final String[] REQUIRED_FLAGS = {DESCRIPTION, DUE_DATE};
}
