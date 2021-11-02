package seedu.duke.ui;

//@@author APZH

import seedu.duke.command.CommandEnum;

import java.util.Scanner;

/**
 * Text UI of the SchedUrMods application.
 */
public class Ui {

    private static final Scanner IN = new Scanner(System.in);

    private static final String LOGO = "\n"
            + " _____        _                _  _   _       ___  ___            _\n"
            + "/  ___|      | |              | || | | |      |  \\/  |           | |\n"
            + "\\ `--.   ___ | |__    ___   __| || | | | _ __ | .  . |  ___    __| | ___\n"
            + " `--. \\ / __|| '_ \\  / _ \\ / _` || | | || '__|| |\\/| | / _ \\  / _` |/ __|\n"
            + "/\\__/ /| (__ | | | ||  __/| (_| || |_| || |   | |  | || (_) || (_| |\\__ \\\n"
            + "\\____/  \\___||_| |_| \\___| \\__,_| \\___/ |_|   \\_|  |_/ \\___/  \\__,_||___/\n"
            + "-------------------------------------------------------------------------\n"
            + "Command-Line Interface for NUSMODS                               (v2.1.0)\n"
            + "-------------------------------------------------------------------------";

    private static final String CURSOR = "[user]: ";

    private static final String LINE_SEPARATOR = "---------------------------------------"
            + "----------------------------------";
    private static final String MESSAGE_BORDER = "|| ";

    private static final String MESSAGE_REGEX = "\\r?\\n";

    /**
     * Prints logo of SchedUrMods upon the start of the application.
     */
    public void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * Prints the cursor used for user input.
     */
    public void printCursor() {
        System.out.print(CURSOR);
    }

    public String readInput() {
        printCursor();
        if (!IN.hasNextLine()) {
            return CommandEnum.BYE.toString();
        }
        String input = IN.nextLine();
        return input;
    }

    //@@author APZH
    /**
     * Prints out the UI display message with a custom formatting after a command is executed.
     * @param message Message to be formatted.
     */
    public void printMessage(String message) {
        String[] messageLines = message.split(MESSAGE_REGEX);
        for (int i = 0; i < messageLines.length; i++) {
            System.out.print(MESSAGE_BORDER + messageLines[i] + "\n");
        }
        System.out.println(LINE_SEPARATOR);
    }

}
