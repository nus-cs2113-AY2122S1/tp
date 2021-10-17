package seedu.traveller;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Ui {
    private static final Logger logger = Logger.getLogger(Ui.class.getName());
    private final Scanner scanner = new Scanner(System.in);

    public Ui() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a ui");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printWelcome() {
        String logo = "  ______                      ____         \n"
                + " /_  __/________ __   _____  / / /__  _____\n"
                + "  / / / ___/ __ `/ | / / _ \\/ / / _ \\/ ___/\n"
                + " / / / /  / /_/ /| |/ /  __/ / /  __/ /    \n"
                + "/_/ /_/   \\__,_/ |___/\\___/_/_/\\___/_/     \n";
        System.out.println(logo);
        System.out.println("Your one stop travel planner.\n");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printTrip(Trip trip) {
        System.out.println(trip);
    }

    public void printAllTrips(TripsList tripsList) {
        if (tripsList.getSize() == 0) {
            System.out.println("\tThere are no trips created yet.");
        } else {
            assert tripsList.getSize() > 0 : "There should be trips in the tripsList.";
            System.out.println("\tHere are all your trips: ");
            for (int i = 0; i < tripsList.getSize(); i++) {
                printTrip(tripsList.getTrip(i));
            }
        }
    }

    public void printExit() {
        System.out.println("\tBye! Have fun on your trips! :)");
    }

    public void printNewTripCreated(String tripName) {
        System.out.println("\tYou have just created a new trip called " + tripName + ".");
    }

    public void printDelete(String tripName) {
        System.out.println("\tYou have just deleted a trip called " + tripName + ".");
    }

    public void printEdit(String tripName) {
        System.out.println("\tYou have just edited a trip called " + tripName + ".");
    }

    public void printSearch(String startCountry, String endCountry, double distance) {
        System.out.println("The distance from " + startCountry + " to " + endCountry + " is " + distance + ".");
    }

    public void printReadSave() {
        System.out.println("Now reading save data.");
    }

    public void printWriteSave() {
        System.out.println("\tNow saving all your trips.");
    }

}