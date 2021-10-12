package seedu.duke;

import java.util.Scanner;

public class Main {

    private static boolean isProgramRunning = true;

    /**
     * Reads and returns user input with leading and trailing whitespaces removed.
     *
     * @param in {@link Scanner} to read user input
     * @return user input with leading and trailing whitespaces removed
     */
    private static String readUserInput(Scanner in) {
        Ui.printPendingCommand();
        return in.nextLine().strip();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        Ui.printWelcome();

        Scanner in = new Scanner(System.in);
        Storage.setScanner(in);

        while (isProgramRunning) {
            isProgramRunning = Parser.parseUserInput(readUserInput(in));
        }

    }

}
