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
        assert !trip.getTripName().equals("all") : "`all` is an invalid tripName.";
        System.out.println(trip);
    }

    public void printAllTrips(TripsList tripsList) {
        assert tripsList.getSize() > 0 : "There are no trips in the tripsList.";
        System.out.println("\tHere are all your trips: ");
        for (int i = 0; i < tripsList.getSize(); i++) {
            printTrip(tripsList.getTrip(i));
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

    public void printDeleteDay(String tripName, int index) {
        System.out.println("\tYou have just deleted day " + index + " of " + tripName);
    }

    public void printDeleteItem(String tripName, int dayIndex, int itemIndex) {
        System.out.println("\tYou have just deleted item " + itemIndex + " of " + tripName + " day " + dayIndex);
    }

    public void printSearchItem(String tripName, String itemName) {
        System.out.println("\tYou have just search item keyword " + itemName + " in trip called " + tripName);
    }

    public void printEditItem(String tripName, int itemIndex, String itemName, String itemTime) {
        System.out.println("\tYou have just edited item " + itemIndex + " of "
                + tripName + " to " + itemName + " and " + itemTime);
    }

    public void printEdit(String tripName) {
        System.out.println("\tYou have just edited a trip called " + tripName + ".");
    }

    public void printShortest(String startCountry, String endCountry, double distance) {
        System.out.println("\tThe distance from " + startCountry + " to " + endCountry + " is " + distance + ".");
    }

    public void printAddDayToTrip(String tripName) {
        System.out.println("\tAdded a new day to trip " + tripName + ".");
    }

    public void printAddItemToDay(String tripName, int dayIndex, String itemName) {
        System.out.println("\tAdded a new item to day " + dayIndex + " of trip " + tripName + ".");
    }

    public void printReadSave() {
        System.out.println("\tNow reading save data.");
    }

    public void printWriteSave() {
        System.out.println("\tNow saving all your trips.");
    }

}
