package seedu.duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Storage {

    public static ArrayList<Trip> listOfTrips = new ArrayList<>();
    private static Trip openTrip = null;

    private static Scanner scanner;

    private static Logger logger;

    private static final ArrayList<String> validCommands = new ArrayList<>(
            Arrays.asList("create", "edit", "view", "open", "list", "summary",
                    "delete", "expense", "quit", "help"));

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Storage.scanner = scanner;
    }

    public static ArrayList<String> getValidCommands() {
        return validCommands;
    }


    public static Trip getOpenTrip() {
        if (openTrip == null) {
            Ui.printNoOpenTripError();
            promptUserForValidTrip();
        }
        return openTrip;
    }

    private static void promptUserForValidTrip() {
        try {
            int tripIndex = Integer.parseInt(scanner.nextLine().strip()) - 1;
            setOpenTrip(listOfTrips.get(tripIndex));
        } catch (NumberFormatException e) {
            Ui.argNotNumber();
        }
    }

    /**
     * Checks if there is an open trip or not.
     *
     * @return true if there is an open trip
     */
    public static boolean checkOpenTrip() {
        return openTrip != null;
    }

    public static void setOpenTrip(Trip openTrip) {
        Storage.openTrip = openTrip;
    }

    public static void closeTrip() {
        Storage.openTrip = null;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        Storage.logger = logger;
    }


}
