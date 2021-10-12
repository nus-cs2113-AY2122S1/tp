package seedu.duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    public static ArrayList<Trip> listOfTrips = new ArrayList<>();
    private static Scanner scanner;
    private static Trip openTrip = null;

    private static final ArrayList<String> validCommands = new ArrayList<>(
            Arrays.asList("create", "edit", "view", "open", "list", "summary", "delete", "expense", "quit"));

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Storage.scanner = scanner;
    }

    public static ArrayList<String> getValidCommands() {
        return validCommands;
    }

    //TODO This function does 2 things, therefore it deserves to be split, like a dysfunction marriage, into 2 functions
    public static Trip getOpenTrip() {
        if (openTrip == null) {
            Ui.printNoOpenTripError();
            try {
                int tripIndex = Integer.parseInt(scanner.nextLine().strip()) - 1;
                setOpenTrip(listOfTrips.get(tripIndex));
            } catch (NumberFormatException e) {
                Ui.argNotNumber();
            }
        }
        return openTrip;
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

}
