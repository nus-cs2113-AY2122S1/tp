package seedu.duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Duke {

    private static boolean isProgramRunning = true;

    private static void beginWelcome() {

        Ui.printWelcome();

        Scanner in = new Scanner(System.in);
        Storage.setScanner(in);
        Logger logger = Logger.getLogger("ProgramLogger");
        Storage.setLogger(logger);

        FileStorage.initializeGson();
        Storage.readFromFile();

    }

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

        beginWelcome();

        while (isProgramRunning) {
            isProgramRunning = Parser.parseUserInput(readUserInput(Storage.getScanner()));
            try {
                Storage.writeToFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
