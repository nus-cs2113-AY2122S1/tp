package seedu.duke;

public class Parser {
    public static Command parse(String input) {
        String[] commandAndParams = splitCommandString(input, " ");
        String command = commandAndParams[0];
        String params = commandAndParams[1];
        switch (command) {
            case "list":
                return new ListCommand();
            case "clear":
                return new ClearCommand();
            default:
                System.out.println("INVALID");
                break;
        }
        return null;
    }

    public static String[] splitCommandString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[] {split[0], ""};
    }
}
