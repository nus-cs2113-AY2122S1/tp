package seedu.duke;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    private static final String CLEAR_MESSAGE = "All clients have been deleted";
    private static final String CUT_MESSAGE = "Client has been deleted:";
    private static final String ADD_MESSAGE = "Client has been added:";
    private static final String BYE_MESSAGE = "Thanks for using TourPlanner. Goodbye!";

    private static final String ADD_TOUR_MESSAGE = "A new tour has been added:";
    private static final String LIST_NO_TOURS_MESSAGE = "I'm sorry, there seems to be no tours.";
    private static final String LIST_TOUR_MESSAGE = "Here is a list of all tours:";
    private static final String FIND_NO_TOURS_MESSAGE = "I'm sorry, there seems no tour that has the code ";
    private static final String FIND_TOUR_MESSAGE = "This is the tour that matches your search";
    private static final String SORT_TOUR_ALPHA_MESSAGE = "Sorted by code alphabetically";
    private static final String SORT_TOUR_PRICE_MESSAGE = "Sorted by price in ascending order";

    private static Scanner in = new Scanner(System.in);

    /**
     * Empty Ui class constructor.
     */
    public Ui() {
        ;
    }

    /**
     * Prints a divider to the text Ui.
     */
    public static void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a welcome message to the user on the text Ui.
     */
    public void showWelcome() {
        showLine();
        String logo = " █████  ███  █   █ ████  ████  █      ████  █    █ █    █ █████ ████\n"
                + "   █   █   █ █   █ █   █ █   █ █     █    █ ██   █ ██   █ █     █   █\n"
                + "   █   █   █ █   █ █████ ████  █     ██████ █ █  █ █ █  █ █████ █████\n"
                + "   █   █   █ █   █ █  █  █     █     █    █ █  █ █ █  █ █ █     █  █\n"
                + "   █    ███   ███  █   █ █     █████ █    █ █   ██ █   ██ █████ █   █\n";
        String greet = "Hello, Welcome to TourPlanner!\n"
                + "What can I do for you?";
        show(logo);
        show(greet);
        showLine();
    }

    /**
     * Prompts for user's input and read's the text entered by the user.
     *
     * @return full input entered by the user
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Outputs to the CLI the text to show the user.
     * Creates a newline as well.
     *
     * @param textToShow the output text intended for the user
     */
    public void show(String textToShow) {
        System.out.print(textToShow + System.lineSeparator());
    }

    /**
     * Ui response to clear client list command.
     */
    public void showClear() {
        show(CLEAR_MESSAGE);
    }

    /**
     * Ui response to cut client command.
     *
     * @param client the client object that was cut
     */
    public void showCut(Client client) {
        show(CUT_MESSAGE + "\n" + client);
    }

    /**
     * Ui response to add client command.
     *
     * @param client the client that was just added
     */
    public void showAdd(Client client) {
        show(ADD_MESSAGE + "\n" + client);
    }

    public void showAddFlight(Flight flight) {
        show(ADD_MESSAGE + "\n" + flight);
    }

    public void showAddTour(Tour tour) {
        show(ADD_MESSAGE + "\n" + tour);
    }

    /**
     * Prints an exit message to the text Ui to acknowledge exiting the application.
     */

    //Tour Functions
    public void showAddTour(Tour tour) {
        show(ADD_TOUR_MESSAGE + "\n" + tour);
    }

    public void showListTour(TourList tours){
        int count = tours.getTourCount();
        if (count == 0) {
            System.out.println(LIST_NO_TOURS_MESSAGE);
            return;
        }
        System.out.println(LIST_TOUR_MESSAGE);
        for (int i = 1; i <= count; i++) {
            Tour currTour = tours.getTourByIndex(i - 1);
            System.out.println(i + ". " + "\n" + currTour);
        }
    }

    public void showFindTour(TourList tours, String code) {
        Tour foundTour = tours.getTourByCode(code);
        if (foundTour != null) {
            System.out.println(FIND_TOUR_MESSAGE + "\n" + foundTour + "\n");
            // List clients that are going for this tour, based on PackageList
        }
        else {
            System.out.println(FIND_NO_TOURS_MESSAGE + code);
        }
    }

    public void showSortedTour(TourList tours, String[] sortedCodes) {
        int count = tours.getTourCount();
        System.out.println(SORT_TOUR_ALPHA_MESSAGE);
        for (int i = 1; i <= count; i++) {
            String currCode = sortedCodes[i - 1];
            Tour currTour = tours.getTourByCode(currCode);
            System.out.println(i + ". " + "\n" + currTour);
        }
    }

    public void showSortedTour(TourList tours, Float[] sortedPrices) {
        int count = tours.getTourCount();
        System.out.println(SORT_TOUR_PRICE_MESSAGE);
        for (int i = 1; i <= count; i++) {
            float currPrice = sortedPrices[i - 1];
            for (int j = 1; j <= count; j++) {
                Tour currTour = tours.getTourByIndex(i - 1);
                if (currTour.getPrice() == currPrice) {
                    System.out.println(i + ". " + "\n" + currTour);
                    break;
                }
            }
        }
    }

    public void showBye() {
        show(BYE_MESSAGE);
        showLine();
    }
}

