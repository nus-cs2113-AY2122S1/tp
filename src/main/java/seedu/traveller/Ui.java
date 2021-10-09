package seedu.traveller;

import java.util.Scanner;


public class Ui {
    private final Scanner scanner = new Scanner(System.in);

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
}
