package taa;

//@@author leyondlee
import taa.command.Command;

import java.util.Scanner;

public class Ui {
    private static final String INPUT_PROMPT = "$ ";

    private static final String MESSAGE_BORDER = "------------------------------------------------------------";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private static final String MESSAGE_FORMAT_EXCEPTION = "An exception has occurred:\n%s";
    private static final String MESSAGE_FORMAT_WELCOME = "Welcome to\n" + "\n"
        + ".___________.    ___           ___\n"
        + "|           |   /   \\         /   \\\n"
        + "`---|  |----`  /  ^  \\       /  ^  \\\n"
        + "    |  |      /  /_\\  \\     /  /_\\  \\\n"
        + "    |  |     /  _____  \\   /  _____  \\\n"
        + "    |__|    /__/     \\__\\ /__/     \\__\\\n" + "\n"
        + "What can I do for you?\n"
        + "Enter \"%s\" to view the list of commands";

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
            System.out.print(inputPrompt);
            input = scanner.nextLine();
        } while (input.trim().isEmpty() && !allowEmpty);

        return input;
    }

    public void printBorder() {
        System.out.println(MESSAGE_BORDER);
    }

    /**
     * Prints message within borders.
     *
     * @param message The message to print.
     */
    public void printMessage(String message) {
        printMessage(message, true);
    }

    public void printMessage(String message, boolean withBorders) {
        if (withBorders) {
            printBorder();
        }

        System.out.println(message);

        if (withBorders) {
            printBorder();
        }
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        printMessage(String.format(MESSAGE_FORMAT_WELCOME, Command.COMMAND_HELP));
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
