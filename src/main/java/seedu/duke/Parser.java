package seedu.duke;

public class Parser {
    public static void parse(String input) {
        String[] commandAndParams = splitCommandString(input, " ");
        String command = commandAndParams[0];
        String params = commandAndParams[1];
    }

    public static String[] splitCommandString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[] {split[0], ""};
    }
}
