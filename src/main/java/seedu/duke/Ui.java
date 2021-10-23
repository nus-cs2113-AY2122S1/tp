package seedu.duke;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String ADD_MESSAGE = " has been added:";
    private static final String LIST_NO_MESSAGE = "I'm sorry, there seems to be no ";
    private static final String LIST_MESSAGE = "Here is a list of all ";


    private static final String BYE_MESSAGE = "Thanks for using TourPlanner. Goodbye!";
    private static final String CLEAR_MESSAGE = "All clients have been deleted";
    private static final String CUT_MESSAGE = "Client has been deleted:";
    private static final String FIND_NO_CLIENTS_MESSAGE = "I'm sorry, there seems no client that matches your search:";
    private static final String FIND_CLIENT_MESSAGE = "This is the client that matches your search:";

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

    //Client Functions
    public void showAddClient(Client client) {
        show("Client" + ADD_MESSAGE + "\n" + client);
    }

    public void showListClient(ClientList clients) {
        int count = clients.getClientCount();
        if (count == 0) {
            System.out.println(LIST_NO_MESSAGE + "clients");
            return;
        }
        System.out.println(LIST_MESSAGE + "clients:");
        for (int i = 1; i <= count; i++) {
            Client currClient = clients.getClient(i - 1);
            System.out.println(i + ". " + currClient + "\n");
        }
        System.out.println("Total Clients:" + count);
    }

    public void showFindClient(ClientList clients, ClientPackageList packages, int index) {
        if (index >= clients.getClientCount()){
           System.out.println(FIND_NO_CLIENTS_MESSAGE);
           return;
        }
        Client foundClient = clients.getClient(index - 1);
        System.out.println(FIND_CLIENT_MESSAGE + "\n" + foundClient);
        System.out.println("Here are the client's packages: ");
        int count = packages.getPackageCount();
        int packageCount = 0;
        for (int i = 0; i < count; i++) {
            ClientPackage currPackage = packages.get(i);
            if (currPackage.getClient().equals(foundClient)) {
                System.out.println((packageCount + 1) + ". " + currPackage + "\n");
                packageCount++;
            }
        }
        System.out.println("Total Packages Subscribed by " + foundClient.getName()+ ": " + packageCount);
    }


    //Tour Functions
    public void showAddTour(Tour tour) {
        show("Tour" + ADD_MESSAGE + "\n" + tour);
    }

    public void showListTour(TourList tours) {
        int count = tours.getTourCount();
        if (count == 0) {
            System.out.println(LIST_NO_MESSAGE + "tours");
            return;
        }
        System.out.println(LIST_MESSAGE + "tours:");
        for (int i = 1; i <= count; i++) {
            Tour currTour = tours.getTourByIndex(i - 1);
            System.out.println(i + ". " + currTour + "\n");
        }
    }

    public void showFindTour(TourList tours, ClientPackageList packages, String code) {
        Tour foundTour = tours.getTourByCode(code);
        if (foundTour != null) {
            System.out.println(FIND_TOUR_MESSAGE + "\n" + foundTour + "\n" + "\n");
            int subbedClients = 0;
            int count = packages.getPackageCount();
            System.out.println("Subscribed Clients:" + "\n");
            for (int i = 0; i < count; i++) {
                Tour currTour = packages.get(i).getTour();
                if (currTour.equals(foundTour)) {
                    String currClientName = packages.get(i).getClient().getName();
                    System.out.println((i + 1) + ". " + currClientName + "\n");
                    subbedClients++;
                }
            }
            System.out.println("Total Subscribed Clients:" + subbedClients);
        } else {
            System.out.println(FIND_NO_TOURS_MESSAGE + code);
        }
    }

    public void showSortedTour(TourList tours, String[] sortedCodes) {
        int count = tours.getTourCount();
        System.out.println(SORT_TOUR_ALPHA_MESSAGE);
        for (int i = 1; i <= count; i++) {
            String currCode = sortedCodes[i - 1];
            Tour currTour = tours.getTourByCode(currCode);
            System.out.println(i + ". " + currTour + "\n");
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
                    System.out.println(i + ". " + currTour + "\n");
                    break;
                }
            }
        }
    }

    //Flight Functions
    public void showAddFlight(Flight flight) {
        show("Flight" + ADD_MESSAGE + "\n" + flight);
    }

    public void showListFlight(FlightList flights) {
        int count = flights.getFlightCount();
        if (count == 0) {
            System.out.println(LIST_NO_MESSAGE + "flights");
            return;
        }
        System.out.println(LIST_MESSAGE + "flights:");
        for (int i = 1; i <= count; i++) {
            Flight currFlight = flights.getFlight(i - 1);
            System.out.println(i + ". " + currFlight + "\n");
        }
    }

    //ClientPackage Functions
    public void showAddClientPackage(ClientPackage clientPackage) {
        show("Client Package" + ADD_MESSAGE + "\n" + clientPackage);
    }

    public void showListClientPackage(ClientPackageList clientPackageList) {
        int count = clientPackageList.getPackageCount();
        if (count == 0) {
            System.out.println(LIST_NO_MESSAGE + "packages");
            return;
        }
        System.out.println(LIST_MESSAGE + "packages:");
        for (int i = 0; i < count; i++) {
            ClientPackage currPackage = clientPackageList.get(i);
            System.out.println((i + 1) + ". " + currPackage + "\n");
        }
        System.out.println("Total Packages:" + count);
    }
    /**
     * Prints an exit message to the text Ui to acknowledge exiting the application.
     */

    public void showBye() {
        show(BYE_MESSAGE);
        showLine();
    }
}

