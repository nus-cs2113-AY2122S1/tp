package seedu.traveller;

import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.objects.Item;
import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Contains all functions that is used to print output to the user's console.
 */
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
        assert !trip.getTripName().equals("all") : "'all' is an invalid tripName.";
        assert !trip.getTripName().equals("") : "'' is an invalid tripName.";
        System.out.println(trip);
    }

    public void printAllTrips(TripsList tripsList) throws TravellerException {
        assert tripsList.getSize() > 0 : "There are no trips in the tripsList.";
        System.out.println("\tHere are all your trips: ");
        for (int i = 0; i < tripsList.getSize(); i++) {
            Trip trip = tripsList.getTrip(i);
            printTrip(trip);
        }
    }

    public void printExit() {
        System.out.println("\tBye! Have fun on your trips! :)");
    }

    public void printNewTripCreated(String tripName) {
        assert !tripName.equals("all") : "'all' is an invalid tripName.";
        assert !tripName.equals("") : "'' is an invalid tripName.";
        System.out.println("\tYou have just created a new trip called " + tripName + ".");
    }

    public void printDelete(String tripName) {
        System.out.println("\tYou have just deleted a trip called " + tripName + ".");
    }

    public void printDeleteDay(String tripName, int index) {
        System.out.println("\tYou have just deleted day " + index + " of " + tripName + ".");
    }

    public void printDeleteItem(String tripName, int dayIndex, int itemIndex) {
        System.out.println("\tYou have just deleted item " + itemIndex + " of " + tripName + " day " + dayIndex + ".");
    }

    public void printSearchItem(String tripName, int dayIndex, String itemKey, ArrayList<Item> keyString) {
        System.out.println("\tYou have just search item keyword " + itemKey
                + " on day " + dayIndex + " in trip called " + tripName + "\n");
        System.out.println("\tResults: ");
        if (keyString.size() == 0) {
            System.out.println("\tThere are no results found.");
        } else {
            int i = 1;
            for (Item key : keyString) {
                System.out.println("\t" + i++ + ". " + key);
            }
        }
    }

    public void printEditItem(String tripName, int dayIndex, String itemName, String itemTime, int itemIndex) {
        System.out.println("\tYou have just edited item " + itemIndex  + " on day " + dayIndex
                + " of " + tripName + " to " + itemName + " at " + itemTime + ".");
    }

    public void printEdit(String tripName) {
        System.out.println("\tYou have just edited a trip called " + tripName + ".");
    }

    public void printShortestTime(String startCountry, String endCountry, double totalTime, List<Double> time) {
        System.out.println("\tThe shortest time from " + startCountry
                + " to " + endCountry + " is " + totalTime + ".");
        System.out.println("\tTime breakdown: ");
        for (Double d : time) {
            System.out.println("\t" + d);
        }
    }


    public void printShortestCost(String startCountry, String endCountry, double cost, List<Double> costs) {
        System.out.println("\tThe least cost from " + startCountry + " to " + endCountry + " is " + cost + ".");
        System.out.println("\tCost breakdown: ");
        for (Double c : costs) {
            System.out.println("\t" + c);
        }
    }


    public void printAddDayToTrip(String tripName, int numberOfDays) {
        assert numberOfDays >= 0 : "Number of days is negative.";
        assert !tripName.equals("all") : "'all' is an invalid tripName.";
        assert !tripName.equals("") : "'' is an invalid tripName.";
        System.out.println("\tAdded " + numberOfDays + " days to trip " + tripName + ".");
    }

    public void printAddItemToDay(String tripName, int dayIndex) {
        assert dayIndex >= 0 : "Number of days is negative.";
        assert !tripName.equals("all") : "'all' is an invalid tripName.";
        assert !tripName.equals("") : "'' is an invalid tripName.";
        System.out.println("\tAdded a new item to day " + dayIndex + " of trip " + tripName + ".");
    }

    public void printReadSave() {
        System.out.println("Now reading save data.");
    }

    public void printWriteSave() {
        System.out.println("\tNow saving all your trips.");
    }

    public void printHelp() {
        System.out.println("\tSome of our basic commands are:\n"
                + "\tnew FamilyTrip2021 /from SIN /to MLY : "
                + "\n\tAdds a new trip called FamilyTrip2021 from Singapore "
                + "(SIN) to Malaysia (MLY).\n"

                + "\tadd-day myTrip 3 : "
                + "Adds 3 days to trip myTrip.\n"

                + "\tadd-item trip1 /day 0 /time 7pm /name Check-in to HolidayInn : "
                + "\n\tAdds item Check-in to HolidayInn to day 0 of trip1.\n"

                + "\tview FamilyTrip2021: Views your trip named FamilyTrip2021 and their detail.\n"

                + "\tdelete FamilyTrip2021 : Deletes the trip called FamilyTrip2021.\n"

                + "\tdelete-day myTrip /day 0 : "
                + "Deletes day 0 of myTrip.\n"

                + "\tdelete-item myTrip /day 0 /item 0 : "
                + "Deletes item 0 of myTrip on day 0.\n"

                + "\tedit FamilyTrip2021 /from SKR /to JPN : "
                + "\n\tEdits an existing trip called FamilyTrip2021"
                + "to have new START and END destinations.\n"

                + "\tedit-item 1 trip1 /day 1 /time 7am /name wake up from bed : "
                + "\n\tEdits item1 of trip 1 to wake up from bed at 7am.\n"

                + "\tsearch-item trip1 /name sleep at home : "
                + "\n\tSearches item keyword sleep at home in trip called trip1.\n"

                + "\tshortest-time /from SKR /to JPN : "
                + "Returns the shortest time from SKR to JPN.\n"

                + "\texit : Exits the program.");
    }

    public void printReadSaveError(int lineNumber) {
        System.out.println("Unable to read line " + lineNumber + ". Save data has been tampered with.");
    }
}
