package expiryeliminator.ui;

import java.util.Scanner;

//@@author bernardboey-reused
// Reused from https://github.com/bernardboey/ip/blob/master/src/main/java/duke/ui/Ui.java
// with minor modifications

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String INDENTED_HORIZONTAL_LINE = " ".repeat(4) + "_".repeat(100);
    private static final String LINE_PREFIX = " ".repeat(5);
    /** Platform independent line separator. */
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String MESSAGE_GREETING = "Welcome to Expiry Eliminator. What can I do for you?";

    /**
     * Reads input commands from the user.
     * Ignores blank lines and trims input command.
     *
     * @return Trimmed input command.
     */
    public String getUserInput() {
        String line = SCANNER.nextLine();
        // Ignore blank lines
        while (line.trim().isEmpty()) {
            line = SCANNER.nextLine();
        }
        return line.trim().toLowerCase();
    }

    /**
     * Prints out the specified text formatted as a block.
     * Horizontal lines will be printed before and after the
     * specified text, and the text will be indented.
     *
     * @param text Text to be printed out.
     */
    public void showToUser(String text) {
        assert text != null;
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(addPrefixAndReplaceNewline(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    /**
     * Prints out greeting message.
     */
    public void showGreeting() {
        showToUser(MESSAGE_GREETING);
    }

    /**
     * Adds {@link #LINE_PREFIX} to the start of each line of {@code text}, and replaces newline characters with the
     * platform independent line separator ({@link #LINE_SEPARATOR}).
     *
     * @param text Text to be processed.
     * @return Processed text.
     */
    private String addPrefixAndReplaceNewline(String text) {
        assert text != null;
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isBlank()) {
                lines[i] = "";
            } else {
                lines[i] = LINE_PREFIX + lines[i];
            }
        }
        return String.join(LINE_SEPARATOR, lines);
    }
    //@@author
}
