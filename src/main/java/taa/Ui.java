package taa;

import java.util.Scanner;

public class Ui {
    private static final String INPUT_PROMPT = "$ ";

    private static final String MESSAGE_BORDER = "------------------------------------------------------------";
    private static final String MESSAGE_WELCOME = "Welcome to\n" + "\n"
            + ".___________.    ___           ___\n"
            + "|           |   /   \\         /   \\\n"
            + "`---|  |----`  /  ^  \\       /  ^  \\\n"
            + "    |  |      /  /_\\  \\     /  /_\\  \\\n"
            + "    |  |     /  _____  \\   /  _____  \\\n"
            + "    |__|    /__/     \\__\\ /__/     \\__\\\n" + "\n"
            + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private static final String MESSAGE_FORMAT_EXCEPTION = "An exception has occurred:\n%s";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets a non-empty user input.
     *
     * @return Non-empty user input.
     */
    public String getUserInput() {
        return getUserInput(INPUT_PROMPT, false);
    }

    public String getUserInput(String inputPrompt, boolean allowEmpty) {
        String input;
        do {
            System.out.print(INPUT_PROMPT);
            input = scanner.nextLine();
        } while (input.trim().isEmpty() && !allowEmpty);

        return input;
    }



    /**
     * Prints message within borders.
     *
     * @param message The message to print.
     */
    public void printMessage(String message) {
        System.out.println(MESSAGE_BORDER);
        System.out.println(message);
        System.out.println(MESSAGE_BORDER);
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        printMessage(MESSAGE_WELCOME);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        printMessage(MESSAGE_EXIT);
    }

    /**
     * Prints an exception message.
     *
     * @param message The message to print.
     */
    public void printException(String message) {
        printMessage(String.format(MESSAGE_FORMAT_EXCEPTION, message));
    }
}
